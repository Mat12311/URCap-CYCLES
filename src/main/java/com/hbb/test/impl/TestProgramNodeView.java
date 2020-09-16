package com.hbb.test.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;

public class TestProgramNodeView implements SwingProgramNodeView <TestProgramNodeContributon> {
	
	private final ViewAPIProvider apiProvider ;
	
	public TestProgramNodeView(ViewAPIProvider apiProvider) {
		this.apiProvider = apiProvider ;
		
	}
	
	private JLabel airTextField = new JLabel("0");
	private JLabel pickTextField = new JLabel("0");
	private JLabel chchTextField = new JLabel("0");
	
	private JSlider airSlider = new JSlider();
	private JSlider pickSlider = new JSlider();
	private JSlider chchSlider = new JSlider();
	
	
	@Override
	public void buildUI(JPanel panel, ContributionProvider<TestProgramNodeContributon> provider) {
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		panel.add(createDescrption("AIR"));
		panel.add(createTextField(airTextField,airSlider, provider));
		panel.add(createSpacer(10));
		panel.add(createSlider(airSlider, 0, 5, airTextField, provider));
		panel.add(createSpacer(20));
		panel.add(createDescrption("PICK"));
		panel.add(createTextField(pickTextField, pickSlider,provider));
		panel.add(createSpacer(10));
		panel.add(createSlider(pickSlider, 0, 20, pickTextField, provider));
		panel.add(createSpacer(20));
		panel.add(createDescrption("CHCH"));
		panel.add(createTextField(chchTextField, chchSlider,provider));
		panel.add(createSpacer(10));
		panel.add(createSlider(chchSlider, 0, 50, chchTextField, provider));
		
	}
	
	public void setAirTextField(int value) {
		airTextField.setText(String.valueOf(value));
		
	}
	public void setPickTextField(int value) {
		pickTextField.setText(String.valueOf(value));
		
	}
	public void setChchTextField(int value) {
		chchTextField.setText(String.valueOf(value));
		
	}
	

	private Box createDescrption(String desc) {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel label = new JLabel(desc);
		box.add(label);
		return box;

}
	private Box createTextField(final JLabel field, final JSlider slider,
			final ContributionProvider<TestProgramNodeContributon> provider) {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JButton plus = new JButton("+");
		plus.setPreferredSize(new Dimension(10,10));
		plus.setMinimumSize(plus.getPreferredSize());
		JButton minus = new JButton("-");
		minus.setPreferredSize(new Dimension(10,10));
		minus.setMinimumSize(plus.getPreferredSize());
		
		field.setPreferredSize(new Dimension(80,30));
		field.setMaximumSize(field.getPreferredSize());
		
		plus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int txt= Integer.parseInt(field.getText());
				txt++;
				if(field==airTextField) {
					provider.get().onAirValueChange(txt);
					if(txt>5) txt=5;
				}
				if(field==pickTextField)provider.get().onPickValueChange(txt);
				if(field==chchTextField)provider.get().onCHCHValueChange(txt);
				slider.setValue(txt);
				field.setText(String.valueOf(txt));
				
				
				
			}
		});
		
		
		minus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int txt= Integer.parseInt(field.getText());
				txt--;
				if(txt<0) txt=0;
				slider.setValue(txt);
				if(field==airTextField)provider.get().onAirValueChange(txt);
				if(field==pickTextField)provider.get().onPickValueChange(txt);
				if(field==chchTextField)provider.get().onCHCHValueChange(txt);
				field.setText(String.valueOf(txt));
				
				
				
			}
		});
		
		
		
		box.add(minus);
		box.add(field);
		box.add(plus);
		
		
		return box;
	}
	
	private Box createSlider(final JSlider slider, int min, int max, final JLabel field,
			final ContributionProvider<TestProgramNodeContributon> provider) {
		
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);
		slider.setMinimum(min);
		slider.setMaximum(max);
		slider.setValue(0);
		slider.setOrientation(JSlider.HORIZONTAL);
		
		slider.setPreferredSize(new Dimension(500,20));
		slider.setMaximumSize(slider.getPreferredSize());
		
		slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int newValue = slider.getValue();
				if(field==airTextField)provider.get().onAirValueChange(newValue);
				if(field==pickTextField)provider.get().onPickValueChange(newValue);
				if(field==chchTextField)provider.get().onCHCHValueChange(newValue);
				field.setText(String.valueOf(newValue));
				
				
			}
		});
	
		box.add(slider);
		
		return box;
	}
	private Component createSpacer(int height) {
		return Box.createRigidArea(new Dimension(0,height));
	}
	
	
	
}
