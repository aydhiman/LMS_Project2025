class Account {
    Issues issuedList;
    public Reader belongsTo;
    Account(Reader belongsTo){
        this.belongsTo = belongsTo;
    }
    void extractIssues(){
        issuedList = Main.allIssues.findIssuedByReader(belongsTo.userId);
        issuedList.displayAll();
    }
}