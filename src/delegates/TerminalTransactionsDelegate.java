package delegates;

import model.Entity.*;

/**
 *
 */
public interface TerminalTransactionsDelegate {
    public void databaseSetup();

    public void deleteSponsor(int sponsorID);
    public void insertSponsor(SponsorModel model);
    public void showSponsors();
    public void updateSponsor(int sponsorID, String newName);

    public void terminalTransactionsFinished();
}
