package com.eg;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Handler;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SFrame extends JFrame {
	private static final int D_WIDTH = 300;
	private static final int D_HEIGHT = 200;

	public static final int TEXTAREA_ROWS = 8;
	public static final int TEXTAREA_COLUMNS = 20;

	public SFrame() {
		add(new SComponent());

		final JTextField usridField = new JTextField();
		usridField.setText("123456");
		final JTextField messageField = new JTextField();

		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(2, 2));
		northPanel.add(new JLabel("UserId: ", SwingConstants.RIGHT));
		northPanel.add(usridField);
		northPanel.add(new JLabel("Message: ", SwingConstants.RIGHT));
		northPanel.add(messageField);

		add(northPanel, BorderLayout.NORTH);

		final JTextArea textArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(textArea);

		add(scrollPane, BorderLayout.CENTER);

		JPanel southPanel = new JPanel();

		JButton insertButton = new JButton("Send");
		southPanel.add(insertButton);
		insertButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = messageField.getText().toString();
				textArea.append("Œ“    £∫" + msg + "\n");
				Tuling.chat(usridField.getText().trim(), msg, new Tuling.OnCallBack() {

					@Override
					public void onSuccess(String response) {
						messageField.setText("");
						textArea.append("–°¡È£∫" + response + "\n");
					}

					@Override
					public void onError() {
						textArea.append("–°¡È£∫" + "«◊£¨‘›ŒﬁÕ¯¬Á¿≤£¨«ÎºÏ≤Èƒ˙µƒÕ¯¬Á…Ë÷√£°" + "\n");
					}
				});

			}
		});
		add(southPanel, BorderLayout.SOUTH);
		pack();

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		setSize(screenWidth / 3, screenHeight / 3);
		setLocationByPlatform(true);
	}
}
