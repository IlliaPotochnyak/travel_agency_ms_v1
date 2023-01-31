package command;

public enum CommandEnum {
    LOGIN { { this.command = new LoginCommand(); } },
    LOGOUT { { this.command = new LogoutCommand(); } },
    LIST_TOUR { { this.command = new ListTourCommand(); } },
    ADD_TOUR { { this.command = new AddTourCommand(); } },
    DELETE_TOUR { { this.command = new DeleteTourCommand(); } },
    EDIT_TOUR { { this.command = new EditTourCommand(); } },
    TOUR_PAGE { { this.command = new TourPageCommand(); } },
    RECEIPT_LIST { { this.command = new ReceiptListCommand(); } },
    USER_LIST { { this.command = new UserListCommand(); } },
    RECEIPT_REGISTER { { this.command = new ReceiptRegisterCommand(); } },
    RECEIPT_STATUS_CHANGE { { this.command = new ReceiptStatusChangeCommand(); } },
    SET_DISCOUNT { { this.command = new SetDiscountCommand(); } },
    USER_ACTIVE { { this.command = new UserActiveCommand(); } },
    LANG_CHANGE { { this.command = new LangChangeCommand(); } },
    REGISTER { { this.command = new RegisterCommand(); } };
        ActionCommand command;

        public ActionCommand getCurrentCommand() {
            return command;
        }

}
