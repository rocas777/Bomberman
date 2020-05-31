package com.noclue.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.noclue.keyboard.KeyBoard;
import com.noclue.keyboard.KeyboardListener;
import com.noclue.model.FieldModel;
import com.noclue.model.MenuModel;
import com.noclue.model.Position;
import com.noclue.model.TimeLeft;
import com.noclue.model.difficulty.Difficulty;
import com.noclue.model.difficulty.Easy;
import com.noclue.model.difficulty.Hard;
import com.noclue.model.difficulty.Medium;
import com.noclue.timer.Timer;
import com.noclue.view.MenuView;
import com.noclue.view.TimeLeftView;
import com.noclue.view.field.FieldView;
import com.noclue.view.field.GameOverView;
import com.noclue.view.field.WinView;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class MenuController implements KeyboardListener {
    MenuModel menuModel;
    MenuView menuView;
    FieldModel fieldModel;
    ArrayList<Difficulty> difficulties = new ArrayList<>();


    public MenuController(MenuModel menuModel, MenuView menuView){
        this.menuModel = menuModel;
        this.menuView = menuView;

    }

    public void setDifficulties(){
        menuModel.setDifficultiesA(readDifficulties(menuModel));
    }

    //reads difficulties from resource file
    public ArrayList<ArrayList<Difficulty>> readDifficulties(MenuModel model) {
        ArrayList<ArrayList<Difficulty>> diffi = new ArrayList<>();
        URL resource = MenuController.class.getClassLoader().getResource("levels.lvl");
        System.out.println(resource);
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(resource.getFile()));
            for (String line; (line = br.readLine()) != null; )
                lines.add(line);
        } catch (IOException i) {
            System.out.println(i);
        }

        model.setLevels("");
        model.setLevel(Integer.parseInt(lines.get(0)));

        for (int li = 1; li < lines.size(); li++) {
            diffi.add(new ArrayList<>());
            String l = lines.get(li);
            model.setLevels(model.getLevels() + l + "\n");

            //sets monster according to each line on resource file
            for (int i = 0; i < l.length(); i++) {
                if (l.charAt(i) == ' ') {

                } else if (l.charAt(i) == 'e') {
                    diffi.get(diffi.size() - 1).add(new Easy());
                } else if (l.charAt(i) == 'm') {
                    diffi.get(diffi.size() - 1).add(new Medium());
                } else if (l.charAt(i) == 'h') {
                    diffi.get(diffi.size() - 1).add(new Hard());
                } else {
                    diffi.get(diffi.size() - 1).add(null);
                }
            }
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return diffi;
    }

    public void killProgram() throws IOException {  //stops execution
        URL resource = MenuController.class.getClassLoader().getResource("levels.lvl");
        BufferedWriter bw = new BufferedWriter(new FileWriter(resource.getFile()));
        bw.write(menuModel.getLevel() + "\n");
        bw.write(menuModel.getLevels());
        bw.close();
        System.exit(0);
    }

    public void run() {
        KeyStroke key;
        boolean inOnLoop = true;
        while (inOnLoop) {
            menuView.draw();
            try {
                key = MenuModel.getScreen().readInput();
                KeyType keyType = key.getKeyType();
                if (keyType == KeyType.EOF) {   //stop execution if terminal is closed abnormaly
                    killProgram();
                    inOnLoop = false;
                }
                //handle menu transitions
                if (key != null && (keyType == KeyType.Character || keyType == KeyType.Enter)) {
                    if (!menuModel.getOnSubMenu()) {    //normal menu
                        if (key.getCharacter() == 'w') {
                            menuModel.optUp();
                        } else if (key.getCharacter() == 's') {
                            menuModel.optDown();
                        } else if (keyType == KeyType.Enter) {  //enter submenu
                            if (chooseOption())
                                inOnLoop = false;
                        }
                    } else {    //submenu
                        if (key.getCharacter() == 'w') {
                            menuModel.subOptUp();
                        } else if (key.getCharacter() == 's') {
                            menuModel.subOptDown();
                        } else if (keyType == KeyType.Enter) {  //leave submenu
                            menuModel.setOnSubMenu(false);
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean chooseOption() {
        if (menuModel.getOption() == 1) {   //if start game is chosen sets approriate difficulty
            if (menuModel.getSubOption() == 1) {
                setEasy();
            } else if (menuModel.getSubOption() == 2) {
                setMedium();
            } else if (menuModel.getSubOption() == 3) {
                setHard();
            }
            startNewGame();
            return true;
        } else if (menuModel.getOption() == 2) {    //enter submenu
            menuModel.setOnSubMenu(true);
        } else if (menuModel.getOption() == 3) {    //stop execution
            try {
                killProgram();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (menuModel.getOption() == 4) {    //continue/start campaign
            difficulties = menuModel.getDifficultiesA().get(menuModel.getLevel() - 1);
            startNewGame();
            return true;
        }
        return false;
    }

    public void startNewGame() {
        //create field model
        fieldModel = new FieldModel(146, 45, menuModel.getLevel());
        fieldModel.setPoints(menuModel.getScore());

        //start timer and keyboard listeners
        Timer t = new Timer(40);
        t.start();

        KeyBoard k = new KeyBoard((TerminalScreen) MenuModel.getScreen());
        k.addListener(this);
        k.start();

        fieldModel.setkServer(k);
        fieldModel.settServer(t);

        //create relevant information for the field controller

        FieldView fieldView = new FieldView(MenuModel.getScreen(), menuModel.getTextGraphics(), fieldModel);
        TimeLeft timeLeft = new TimeLeft(120, new Position(146, 45, 138, 30));
        fieldView.setTimeLeftView(new TimeLeftView(timeLeft, menuModel.getTextGraphics()));
        FieldController fieldController = new FieldController(fieldModel, fieldView, new GameOverView(MenuModel.getScreen(), menuModel.getTextGraphics()), new WinView(MenuModel.getScreen(), menuModel.getTextGraphics()), menuModel.getTextGraphics(), timeLeft);


        fieldController.setDifficulty(difficulties);

        //setup the map
        fieldController.setup();

        //subscribe field model to the listeners
        fieldModel.gettServer().addListener(fieldController);
        fieldModel.getkServer().addListener(fieldController);
    }

    @Override
    public void updateOnKeyboard(KeyStroke keyPressed) {
        if (keyPressed.getKeyType() == KeyType.EOF) {
            try {
                killProgram();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (keyPressed.getCharacter() == 'q') { //returning to menu screen
            //unsubscribe field from stuff
            fieldModel.gettServer().removeListeners();
            fieldModel.getkServer().removeListeners();
            //stop the time and keyboard loops
            fieldModel.getkServer().stop();
            fieldModel.gettServer().stop();
            fieldModel.setkServer(null);
            fieldModel.settServer(null);
            //updater info if level was completed successfully
            if (fieldModel.isWon()) {
                menuModel.setLevel((menuModel.getLevel() + 1) % menuModel.getDifficultiesA().size());
                menuModel.setScore(fieldModel.getPoints());
            }

            System.out.println("Nivel" + menuModel.getLevel());
            fieldModel = null;
            difficulties = new ArrayList<>();
            try {
                MenuModel.getScreen().refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //go back to normal menu execution
            run();
        }
    }

    //for start game option

    private void setHard() {    //sets 7 monster of hard difficulty
        difficulties.clear();
        for (int i = 0; i < 8; i++) {
            difficulties.add(new Hard());
        }

    }

    private void setMedium() {  //sets 6 monster of medium difficulty
        difficulties.clear();
        for (int i = 0; i < 7; i++) {
            difficulties.add(new Medium());
        }

    }

    private void setEasy() {    //sets 5 monster of easy difficulty
        difficulties.clear();
        for (int i = 0; i < 6; i++) {
            difficulties.add(new Easy());
        }
    }
}
