package com.eg;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JComponent;

public class SComponent extends JComponent {
	public static final int MSG_X = 100;
	public static final int MSG_Y = 100;

	public static final int D_W = 700;
	public static final int D_H = 400;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(D_W, D_H);
	}
}
