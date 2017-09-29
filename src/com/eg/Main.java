package com.eg;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

public class Main {

	public Main() {
		super();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				SFrame frame = new SFrame();
				frame.setTitle("Title");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}