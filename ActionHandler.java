package crank;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class ActionHandler extends AbstractAction{
	private String cmd;
	public ActionHandler(String cmd) {
		this.cmd = cmd;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(cmd.equals("RightArrow")) {
			Game.getPlayer().moveHoz(1);
		}
		if (cmd.equals("RightArrowRelease")) {
			Game.getPlayer().moveHoz(0);
		}
		
		if(cmd.equals("LeftArrow")) {
			Game.getPlayer().moveHoz(-1);
		}
		if (cmd.equals("LeftArrowRelease")) {
			Game.getPlayer().moveHoz(0);
		}
		if (cmd.equals("f5")) {
			Game.restart();
		}
	}
}
