package model.command;

import model.api.Command;
import model.api.PlayBox;

public class PlayMusicCommand implements Command {

    private PlayBox playBox;
    public PlayMusicCommand(PlayBox playBox) {
        this.playBox = playBox;
    }

    @Override
    public void execute() {
        playBox.play();
    }
}
