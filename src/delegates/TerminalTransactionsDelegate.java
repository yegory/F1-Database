package delegates;

import model.Sponsor;

/**
 *
 */
public interface TerminalTransactionsDelegate {
    public void databaseSetup();

    public void deleteSponsor(int sponsorID);
    public void insertSponsor(Sponsor model);
    public void showSponsors();
    public void updateSponsor(int sponsorID, String newName);

    public void terminalTransactionsFinished();
}
