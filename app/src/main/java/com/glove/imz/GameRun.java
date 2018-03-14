package com.glove.imz;

import com.badlogic.gdx.Game;
import com.glove.imz.screens.main_menu.MainMenu;
import com.glove.imz.screens.level_labs.*;

public class GameRun extends Game
{
	MainMenu mainMenu;
	LevelLabs levelLabs;
	
	@Override
	public void create()
	{
		//mainMenu = new MainMenu();
		//setScreen(mainMenu);
		levelLabs = new LevelLabs();
		setScreen(levelLabs);
	}
}
