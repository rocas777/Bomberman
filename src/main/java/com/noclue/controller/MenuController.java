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
    private MenuModel menuModel;
    private MenuView menuView;
    private FieldModel fieldModel;
    private ArrayList<Difficulty> difficulties = new ArrayList<>();


    public MenuController(MenuModel menuModel, MenuView menuView) {
        this.setMenuModel(menuModel);
        this.setMenuView(menuView);

    }

    public void setDifficulties() {
        getMenuModel().setDifficultiesA(readDifficulties(getMenuModel()));
    }

    //reads difficulties from resource file
    public ArrayList<ArrayList<Difficulty>> readDifficulties(MenuModel model) {
        ArrayList<ArrayList<Difficulty>> diffi = new ArrayList<>();
        URL resource = MenuController.class.getClassLoader().getResource("levels.lvl");
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(resource.getFile()));
            for (String line; (line = br.readLine()) != null; )
                lines.add(line);
        } catch (IOException i) {
            System.out.println("Couldn't open File levels.lvl");
        }

        model.setLevels("");
        model.setLevel(Integer.parseInt(lines.get(0)));


        getMenuModel().setScore(0);
        getMenuModel().setLevel(1);
        BufferedReader bw = null;
        try {
            bw = new BufferedReader(new FileReader("./currentlevel.lvl"));
            getMenuModel().setLevel(bw.read());
            getMenuModel().setScore(bw.read());
            bw.close();
        } catch (IOException e) {
            System.out.println("Couldn't open File currentlevel.lvl");
        }

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

    public void killProgram() throws IOException {//stops execution and stores level and score info
        BufferedWriter bw = new BufferedWriter(new FileWriter("./currentlevel.lvl"));
        if (getMenuModel().getLevel() <= 21) {
            bw.write(getMenuModel().getLevel());
            bw.write(getMenuModel().getScore());
        } else {
            bw.write("1");
            bw.write("0");
        }
        bw.close();
        System.exit(0);
    }

    public void run() {
        KeyStroke key;
        boolean inOnLoop = true;
        while (inOnLoop) {
            getMenuView().draw();
            try {
                key = MenuModel.getScreen().readInput();
                KeyType keyType = key.getKeyType();
                if (keyType == KeyType.EOF) {   //stop execution if terminal is closed abnormaly
                    killProgram();
                    inOnLoop = false;
                }
                //handle menu transitions
                if (key != null && (keyType == KeyType.Character || keyType == KeyType.Enter)) {
                    if (!getMenuModel().getOnSubMenu()) {    //normal menu
                        if (key.getCharacter() == 'w') {
                            getMenuModel().optUp();
                        } else if (key.getCharacter() == 's') {
                            getMenuModel().optDown();
                        } else if (keyType == KeyType.Enter) {  //enter submenu
                            if (chooseOption())
                                inOnLoop = false;
                        }
                    } else {    //submenu
                        if (key.getCharacter() == 'w') {
                            getMenuModel().subOptUp();
                        } else if (key.getCharacter() == 's') {
                            getMenuModel().subOptDown();
                        } else if (keyType == KeyType.Enter) {  //leave submenu
                            getMenuModel().setOnSubMenu(false);
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean chooseOption() {
        switch (getMenuModel().getOption()) {
            case 1:
                switch (getMenuModel().getSubOption()) {
                    case 1:
                        setEasy();
                        break;
                    case 2:
                        setMedium();
                        break;
                    case 3:
                        setHard();
                }
                startNewGame();
                return true;
            case 2:
                getMenuModel().setOnSubMenu(true);
                break;
            case 3:
                try {
                    killProgram();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                setDifficulties(getMenuModel().getDifficultiesA().get(getMenuModel().getLevel() - 1));
                startNewGame();
                return true;
        }
        return false;
    }

    public void startNewGame() {
        //create field model
        setFieldModel(new FieldModel(146, 45, getMenuModel().getLevel()));
        //se for campanha poe os pontos da campanha, senão poes 0
        if (getMenuModel().getOption() == 4)
            getFieldModel().setPoints(getMenuModel().getScore());
        else
            getFieldModel().setPoints(0);

        //start timer and keyboard listeners
        Timer t = new Timer(40);
        t.start();

        KeyBoard k = new KeyBoard((TerminalScreen) MenuModel.getScreen());
        k.addListener(this);
        k.start();

        getFieldModel().setkServer(k);
        getFieldModel().settServer(t);

        //create relevant information for the field controller

        FieldView fieldView = new FieldView(MenuModel.getScreen(), getMenuModel().getTextGraphics(), getFieldModel());
        TimeLeft timeLeft = new TimeLeft(120, new Position(146, 45, 138, 30));
        fieldView.setTimeLeftView(new TimeLeftView(timeLeft, getMenuModel().getTextGraphics()));
        FieldController fieldController = new FieldController(getFieldModel(), fieldView, new GameOverView(MenuModel.getScreen(), getMenuModel().getTextGraphics()), new WinView(MenuModel.getScreen(), getMenuModel().getTextGraphics()), getMenuModel().getTextGraphics(), timeLeft);


        fieldController.setDifficulty(getDifficulties());

        //setup the map
        fieldController.setup();

        //subscribe field model to the listeners
        getFieldModel().gettServer().addListener(fieldController);
        getFieldModel().getkServer().addListener(fieldController);
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
            getFieldModel().gettServer().removeListeners();
            getFieldModel().getkServer().removeListeners();
            //stop the time and keyboard loops
            getFieldModel().getkServer().stop();
            getFieldModel().gettServer().stop();
            getFieldModel().setkServer(null);
            getFieldModel().settServer(null);
            //updater info if level was completed successfully
            if (getFieldModel().isWon()) {
                if (getMenuModel().getOption() == 4) {
                    if (getMenuModel().getLevel() < 21)
                        getMenuModel().setLevel((getMenuModel().getLevel()) + 1);
                    else
                        getMenuModel().setLevel(1);
                    getMenuModel().setScore(getFieldModel().getPoints());
                }
            }
            setFieldModel(null);
            setDifficulties(new ArrayList<>());
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
        getDifficulties().clear();
        for (int i = 0; i < 8; i++) {
            getDifficulties().add(new Hard());
        }

    }

    private void setMedium() {  //sets 6 monster of medium difficulty
        getDifficulties().clear();
        for (int i = 0; i < 7; i++) {
            getDifficulties().add(new Medium());
        }

    }

    private void setEasy() {    //sets 5 monster of easy difficulty
        getDifficulties().clear();
        for (int i = 0; i < 6; i++) {
            getDifficulties().add(new Easy());
        }
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public MenuView getMenuView() {
        return menuView;
    }

    public void setMenuView(MenuView menuView) {
        this.menuView = menuView;
    }

    public FieldModel getFieldModel() {
        return fieldModel;
    }

    public void setFieldModel(FieldModel fieldModel) {
        this.fieldModel = fieldModel;
    }

    public ArrayList<Difficulty> getDifficulties() {
        return difficulties;
    }

    public void setDifficulties(ArrayList<Difficulty> difficulties) {
        this.difficulties = difficulties;
    }
}
