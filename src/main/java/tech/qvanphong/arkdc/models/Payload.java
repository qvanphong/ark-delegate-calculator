package tech.qvanphong.arkdc.models; 
import com.fasterxml.jackson.annotation.JsonProperty; 
public class Payload{
    @JsonProperty("voters_zero_balance") 
    public int getVoters_zero_balance() { 
		 return this.voters_zero_balance; } 
    public void setVoters_zero_balance(int voters_zero_balance) { 
		 this.voters_zero_balance = voters_zero_balance; } 
    int voters_zero_balance;
    @JsonProperty("voters_not_zero_balance") 
    public int getVoters_not_zero_balance() { 
		 return this.voters_not_zero_balance; } 
    public void setVoters_not_zero_balance(int voters_not_zero_balance) { 
		 this.voters_not_zero_balance = voters_not_zero_balance; } 
    int voters_not_zero_balance;
}
