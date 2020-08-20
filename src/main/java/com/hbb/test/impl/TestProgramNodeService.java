package com.hbb.test.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.ContributionConfiguration;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.data.DataModel;

public class TestProgramNodeService implements SwingProgramNodeService <TestProgramNodeContributon, TestProgramNodeView>{

	@Override
	public String getId() {
		
		return "CYCLES";
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
	configuration.setChildrenAllowed(false);
		
	}

	@Override
	public String getTitle(Locale locale) {
		
		return "CYCLES";
	}

	@Override
	public TestProgramNodeView createView(ViewAPIProvider apiProvider) {
		
		return new TestProgramNodeView(apiProvider);
	}

	@Override
	public TestProgramNodeContributon createNode(ProgramAPIProvider apiProvider, TestProgramNodeView view,
			DataModel model, CreationContext context) {
		
		return new TestProgramNodeContributon(apiProvider, view, model);
	}

}
