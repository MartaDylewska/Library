package card;

import java.time.LocalDate;
import java.util.Objects;

public class Card {
    private LocalDate issueDate;
    private int idCard;

    public Card(LocalDate issueDate){
        this.issueDate = issueDate;
    }

    public Card( int idCard, LocalDate issueDate){
        this.idCard = idCard;
        this.issueDate = issueDate;
    }

    public Card(){}

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return getIdCard() == card.getIdCard() &&
                Objects.equals(getIssueDate(), card.getIssueDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIssueDate(), getIdCard());
    }

    @Override
    public String toString() {
        return "Card{" +
                "issueDate=" + issueDate +
                ", idCard=" + idCard +
                '}';
    }
}
