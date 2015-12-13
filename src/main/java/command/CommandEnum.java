package command;


public enum CommandEnum {
    IMPORT{
        {
           // this.command = new ImportCommand();
        }
    },
    SHOW{
        {
            this.command = new ShowCommand();
        }
    },
    PHONE{
        {
            this.command = new PhoneCommand();
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
