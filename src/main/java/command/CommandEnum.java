package command;

/**
 * Created by zhenya on 24.10.15.
 */
public enum CommandEnum {
    IMPORT{
        {
           // this.command = new ImportCommand();
        }
    },
    SHOW{
        {
            // this.command = new ShowCommand();
        }
    },
    DELETE{
        {
           // this.command = new DeleteCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
