package command;


public enum CommandEnum {
    SAVE{
        {
            this.command = new SaveCommand();
        }
    },
    SHOW{
        {
            this.command = new ShowCommand();
        }
    },
    DELETE{
        {
            this.command = new DeleteCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
