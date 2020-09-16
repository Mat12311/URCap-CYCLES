package com.hbb.test.impl;

import com.ur.urcap.api.contribution.ProgramNodeContribution;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.undoredo.UndoRedoManager;
import com.ur.urcap.api.domain.undoredo.UndoableChanges;

public class TestProgramNodeContributon implements ProgramNodeContribution {
	
	private final ProgramAPIProvider apiProvider;
	private final TestProgramNodeView view;
	private final DataModel model;
	private final UndoRedoManager undoRedoManager;
	
	private static final String AIR_KEY = "air";
	private static final int DEFAULT_AIR = 0;
	private static final String PICK_KEY = "pick";
	private static final int DEFAULT_PICK = 0;
	private static final String CHCH_KEY = "chch";
	private static final int DEFAULT_CHCH = 0;
	
	public TestProgramNodeContributon(ProgramAPIProvider apiProvider, TestProgramNodeView view, DataModel model) {
		
		this.apiProvider = apiProvider;
		this.view = view;
		this.model=model;
		this.undoRedoManager = this.apiProvider.getProgramAPI().getUndoRedoManager();
		
	}
	
	public void onAirValueChange(final int val) {
		undoRedoManager.recordChanges(new UndoableChanges() {
			
			@Override
			public void executeChanges() {
				model.set(AIR_KEY,val);
				
			}
		});
	}
	
	private int getAir() {
		return model.get(AIR_KEY,DEFAULT_AIR);
	}
	
	public void onPickValueChange(final int val) {
		undoRedoManager.recordChanges(new UndoableChanges() {
			
			@Override
			public void executeChanges() {
				model.set(PICK_KEY,val);
				
			}
		});
	}
	
	private int getPICK() {
		return model.get(PICK_KEY,DEFAULT_PICK);
	}
	
	public void onCHCHValueChange(final int val) {
		undoRedoManager.recordChanges(new UndoableChanges() {
			
			@Override
			public void executeChanges() {
				model.set(CHCH_KEY,val);
				
			}
		});
	}
	
	private int getCHCH() {
		return model.get(CHCH_KEY,DEFAULT_CHCH);
	}
	
	

	@Override
	public void openView() {
		view.setAirTextField(getAir());
		view.setPickTextField(getPICK());
		view.setChchTextField(getCHCH());
		
		
	}

	@Override
	public void closeView() {
		
	}

	@Override
	public String getTitle() {
		
		return "Test CYCLES";
	}

	@Override
	public boolean isDefined() {
		
		return true;
	}

	@Override
	public void generateScript(ScriptWriter writer) {
		writer.appendLine("global hbb_air = "+getAir()+"");
		writer.appendLine("global hbb_pick = "+getPICK()+"");
		writer.appendLine("global hbb_chch = "+getCHCH()+"");
	
	}

}
