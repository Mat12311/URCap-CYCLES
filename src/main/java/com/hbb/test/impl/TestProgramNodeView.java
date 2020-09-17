package com.hbb.test.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
	
	private JLabel airTextLabel = new JLabel("0");
	private JLabel pickTextLabel = new JLabel("0");
	private JLabel chchTextLabel = new JLabel("0");
	
	private JSlider airSlider = new JSlider();
	private JSlider pickSlider = new JSlider();
	private JSlider chchSlider = new JSlider();
	
	
	@Override
	public void buildUI(JPanel panel, ContributionProvider<TestProgramNodeContributon> provider) {
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		panel.add(createDescrption("AIR"));
		panel.add(createTextField(airTextLabel,airSlider, provider));
		panel.add(createSpacer(10));
		panel.add(createSlider(airSlider, 0, 5, airTextLabel, provider));
		panel.add(createSpacer(20));
		panel.add(createDescrption("PICK"));
		panel.add(createTextField(pickTextLabel, pickSlider,provider));
		panel.add(createSpacer(10));
		panel.add(createSlider(pickSlider, 0, 20, pickTextLabel, provider));
		panel.add(createSpacer(20));
		panel.add(createDescrption("CHCH"));
		panel.add(createTextField(chchTextLabel, chchSlider,provider));
		panel.add(createSpacer(10));
		panel.add(createSlider(chchSlider, 0, 50, chchTextLabel, provider));
		
	}
	
	public void setAirTextLabel(int value) {
		airTextLabel.setText(String.valueOf(value));
		
	}
	public void setPickTextLabel(int value) {
		pickTextLabel.setText(String.valueOf(value));
		
	}
	public void setChchTextLabel(int value) {
		chchTextLabel.setText(String.valueOf(value));
		
	}
	
	public void setAirSlider(int value) {
		airSlider.setValue(value);
		
	}
	public void setPickSlider(int value) {
		pickSlider.setValue(value);
		
	}
	public void setChchSlder(int value) {
		chchSlider.setValue(value);
		
	}
	

	private Box createDescrption(String desc) {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel label = new JLabel(desc);
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		box.add(label);
		return box;

}
	private Box createTextField(final JLabel label, final JSlider slider,
			final ContributionProvider<TestProgramNodeContributon> provider) {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JButton plus = new JButton("+");
		plus.setPreferredSize(new Dimension(10,10));
		plus.setMinimumSize(plus.getPreferredSize());
		JButton minus = new JButton("-");
		minus.setPreferredSize(new Dimension(10,10));
		minus.setMinimumSize(plus.getPreferredSize());
		
		label.setPreferredSize(new Dimension(40,30));
		label.setMaximumSize(label.getPreferredSize());
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		//field.setFont(field.getFont().deriveFont(Font.LAYOUT_LEFT_TO_RIGHT));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		
		
		
		plus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int txt= Integer.parseInt(label.getText());
				txt++;
				if(label==airTextLabel) {
					provider.get().onAirValueChange(txt);
					if(txt>5) txt=5;
				}
				if(label==pickTextLabel)provider.get().onPickValueChange(txt);
				if(label==chchTextLabel)provider.get().onCHCHValueChange(txt);
				slider.setValue(txt);
				label.setText(String.valueOf(txt));
				
				
				
			}
		});
		
		
		minus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int txt= Integer.parseInt(label.getText());
				txt--;
				if(txt<0) txt=0;
				slider.setValue(txt);
				if(label==airTextLabel)provider.get().onAirValueChange(txt);
				if(label==pickTextLabel)provider.get().onPickValueChange(txt);
				if(label==chchTextLabel)provider.get().onCHCHValueChange(txt);
				label.setText(String.valueOf(txt));
				
				
				
			}
		});
		
		
		
		box.add(minus);
		box.add(label);
		box.add(plus);
		
		
		return box;
	}
	
	private Box createSlider(final JSlider slider, int min, int max, final JLabel label,
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
				if(label==airTextLabel)provider.get().onAirValueChange(newValue);
				if(label==pickTextLabel)provider.get().onPickValueChange(newValue);
				if(label==chchTextLabel)provider.get().onCHCHValueChange(newValue);
				label.setText(String.valueOf(newValue));
				
				
			}
		});
	
		box.add(slider);
		
		return box;
	}
	private Component createSpacer(int height) {
		return Box.createRigidArea(new Dimension(0,height));
	}
	
	
	
}
