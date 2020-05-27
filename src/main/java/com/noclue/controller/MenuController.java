package com.noclue.controller;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
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


    public MenuController(MenuModel menuModel, MenuView menuView) throws IOException {
        this.menuModel = menuModel;
        this.menuView = menuView;

        menuModel.setDifficultiesA(readDifficulties(menuModel));
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(146, 45)).createTerminal();
        MenuModel.setScreen(new TerminalScreen(terminal));
        MenuModel.getScreen().setCursorPosition(null);   // we don't need a cursor
        MenuModel.getScreen().startScreen();             // screens must be started
        MenuModel.getScreen().doResizeIfNecessary();     // resize screen if necessary

        menuModel.setTextGraphics(MenuModel.getScreen().newTextGraphics());


    }

    private static ArrayList<ArrayList<Difficulty>> readDifficulties(MenuModel model) {
        ArrayList<ArrayList<Difficulty>> diffi = new ArrayList<>();
        URL resource = MenuController.class.getClassLoader().getResource("levels.lvl");
        System.out.println(resource);
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(resource.getFile()));
        } catch (FileNotFoundException f) {
            System.out.println(f);
        }
        try {
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
            for (int i = 0; i < l.length(); i++) {
                if (l.charAt(i) == ' ') {

                } else if (l.charAt(i) == 'e') {
                    diffi.get(diffi.size() - 1).add(new Easy());
                } else if (l.charAt(i) == 'm') {
                    diffi.get(diffi.size() - 1).add(new Medium());
                } else if (l.charAt(i) == 'h') {
                    diffi.get(diffi.size() - 1).add(new Hard());
                } else {
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

    public void run() {
        KeyStroke key;
        while (true) {
            menuView.draw();
            try {
                key = MenuModel.getScreen().readInput();
                if(key.getKeyType()==KeyType.EOF) {
                    URL resource = MenuController.class.getClassLoader().getResource("levels.lvl");
                    BufferedWriter bw = new BufferedWriter(new FileWriter(resource.getFile()));
                    bw.write(menuModel.getLevel() + "\n");
                    bw.write(menuModel.getLevels());
                    bw.close();
                    System.exit(0);
                }
                if (key != null && (key.getKeyType() == KeyType.Character || key.getKeyType() == KeyType.Enter)) {
                    if (!menuModel.getOnSubMenu()) {
                        if (key.getCharacter() == 'w') {
                            menuModel.optUp();
                        } else if (key.getCharacter() == 's') {
                            menuModel.optDown();
                        } else if (key.getKeyType() == KeyType.Enter) {
                            if (menuModel.getOption() == 3) {
                                URL resource = MenuController.class.getClassLoader().getResource("levels.lvl");
                                BufferedWriter bw = new BufferedWriter(new FileWriter(resource.getFile()));
                                bw.write(menuModel.getLevel() + "\n");
                                bw.write(menuModel.getLevels());
                                bw.close();
                                System.exit(0);
                            } else if (menuModel.getOption() == 2) {
                                menuModel.setOnSubMenu(true);
                            } else if (menuModel.getOption() == 1) {
                                if (menuModel.getSubOption() == 1) {
                                    setEasy();
                                } else if (menuModel.getSubOption() == 2) {
                                    setMedium();
                                } else if (menuModel.getSubOption() == 3) {
                                    setHard();
                                }
                                startNewGame();
                                break;
                            } else if (menuModel.getOption() == 4) {
                                difficulties = menuModel.getDifficultiesA().get(menuModel.getLevel() - 1);
                                startNewGame();
                                break;
                            }
                        }
                    } else {
                        if (key.getCharacter() == 'w') {
                            menuModel.subOptUp();
                        } else if (key.getCharacter() == 's') {
                            menuModel.subOptDown();
                        } else if (key.getKeyType() == KeyType.Enter) {
                            menuModel.setOnSubMenu(false);
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void startNewGame() {
        fieldModel = new FieldModel(146, 45, menuModel.getLevel());
        fieldModel.setPoints(menuModel.getScore());

        Timer t = new Timer(40);
        t.start();

        KeyBoard k = new KeyBoard((TerminalScreen) MenuModel.getScreen());
        k.addListener(this);
        k.start();

        fieldModel.setkServer(k);
        fieldModel.settServer(t);

        FieldView fieldView = new FieldView(MenuModel.getScreen(), menuModel.getTextGraphics(), fieldModel);
        TimeLeft timeLeft = new TimeLeft(120, new Position(146, 45, 138, 30));
        fieldView.setTimeLeftView(new TimeLeftView(timeLeft, menuModel.getTextGraphics()));
        FieldController fieldController = new FieldController(fieldModel, fieldView, new GameOverView(MenuModel.getScreen(), menuModel.getTextGraphics()), new WinView(MenuModel.getScreen(), menuModel.getTextGraphics()), menuModel.getTextGraphics(), timeLeft);


        fieldController.setDifficulty(difficulties);

        fieldController.setup();
        fieldModel.gettServer().addListener(fieldController);
        fieldModel.getkServer().addListener(fieldController);
    }

    @Override
    public void updateOnKeyboard(KeyStroke keyPressed) {
        if(keyPressed.getKeyType()==KeyType.EOF) {
            URL resource = MenuController.class.getClassLoader().getResource("levels.lvl");
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(resource.getFile()));
                bw.write(menuModel.getLevel() + "\n");
                bw.write(menuModel.getLevels());
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
        if (keyPressed.getCharacter() == 'q') {
            fieldModel.gettServer().removeListeners();
            fieldModel.getkServer().removeListeners();
            fieldModel.getkServer().stop();
            fieldModel.gettServer().stop();
            fieldModel.setkServer(null);
            fieldModel.settServer(null);
            if (fieldModel.isWon()) {
                menuModel.setLevel(menuModel.getLevel() + 1);
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
            run();
        }
    }

    private void setHard() {
        difficulties.clear();
        difficulties.add(new Hard());
        difficulties.add(new Hard());
        difficulties.add(new Hard());
        difficulties.add(new Hard());
        difficulties.add(new Hard());
        difficulties.add(new Hard());
        difficulties.add(new Hard());
        difficulties.add(new Hard());

    }

    private void setMedium() {
        difficulties.clear();
        difficulties.add(new Medium());
        difficulties.add(new Medium());
        difficulties.add(new Medium());
        difficulties.add(new Medium());
        difficulties.add(new Medium());
        difficulties.add(new Medium());
        difficulties.add(new Medium());

    }

    private void setEasy() {
        difficulties.clear();
        difficulties.add(new Easy());
        difficulties.add(new Easy());
        difficulties.add(new Easy());
        difficulties.add(new Easy());
        difficulties.add(new Easy());
        difficulties.add(new Easy());
    }
}
