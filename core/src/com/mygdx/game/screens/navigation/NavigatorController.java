package com.mygdx.game.screens.navigation;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.screens.main_menu.MainMenuView;

public class NavigatorController implements Disposable {

    private Screen screen;
    public NavigatorController() {
        this.changeScreen(NavigationModel.NavigationScreen.MAINMENU);
    }

    public void changeScreen(NavigationModel.NavigationScreen screen) {
        switch(screen) {
            case MAINMENU:
                MainMenuView mainMenuView = new MainMenuView();
                this.setScreen(mainMenuView);

        }
    }

    public void setScreen(Screen screen) {
        if (this.screen != null) {
            this.screen.hide();
            this.screen.dispose();
        }
        this.screen = screen;
        if (this.screen != null) {
            this.screen.show();
        }
    }

    public Screen getScreen() {
        return screen;
    }

    @Override
    public void dispose() {
        if (this.screen != null) {
            this.screen.dispose();
        }
        this.screen = null;
    }
}
