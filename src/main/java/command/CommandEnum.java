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
    AVATAR{
        {
            this.command = new AvatarCommand();
        }
    },

    DELETE{
        {
            this.command = new DeleteCommand();
        }
    },

    SETATTACH{
        {
            this.command = new SetAttachCommand();
        }
    },

    SEARCH{
        {
            this.command = new SearchCommand();
        }
    },

    ATTACH{
        {
            this.command = new AttachCommand();
        }
    },

    GET{
        {
            this.command = new GetCommand();
        }
    };

    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
