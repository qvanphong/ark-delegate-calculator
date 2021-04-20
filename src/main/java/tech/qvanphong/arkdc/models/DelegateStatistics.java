package tech.qvanphong.arkdc.models; 
import com.fasterxml.jackson.annotation.JsonProperty; 
public class DelegateStatistics{
    @JsonProperty("id") 
    public int getId() { 
		 return this.id; } 
    public void setId(int id) { 
		 this.id = id; } 
    int id;
    @JsonProperty("delegate_id") 
    public int getDelegate_id() { 
		 return this.delegate_id; } 
    public void setDelegate_id(int delegate_id) { 
		 this.delegate_id = delegate_id; } 
    int delegate_id;
    @JsonProperty("voters") 
    public int getVoters() { 
		 return this.voters; } 
    public void setVoters(int voters) { 
		 this.voters = voters; } 
    int voters;
    @JsonProperty("approval") 
    public String getApproval() { 
		 return this.approval; } 
    public void setApproval(String approval) { 
		 this.approval = approval; } 
    String approval;
    @JsonProperty("rank") 
    public int getRank() { 
		 return this.rank; } 
    public void setRank(int rank) { 
		 this.rank = rank; } 
    int rank;
    @JsonProperty("forged") 
    public String getForged() { 
		 return this.forged; } 
    public void setForged(String forged) { 
		 this.forged = forged; } 
    String forged;
    @JsonProperty("voting_power") 
    public String getVoting_power() { 
		 return this.voting_power; } 
    public void setVoting_power(String voting_power) { 
		 this.voting_power = voting_power; } 
    String voting_power;
    @JsonProperty("payload") 
    public Payload getPayload() { 
		 return this.payload; } 
    public void setPayload(Payload payload) { 
		 this.payload = payload; } 
    Payload payload;
    @JsonProperty("rank_changed") 
    public int getRank_changed() { 
		 return this.rank_changed; } 
    public void setRank_changed(int rank_changed) { 
		 this.rank_changed = rank_changed; } 
    int rank_changed;
    @JsonProperty("created_at") 
    public String getCreated_at() { 
		 return this.created_at; } 
    public void setCreated_at(String created_at) { 
		 this.created_at = created_at; } 
    String created_at;
    @JsonProperty("updated_at") 
    public String getUpdated_at() { 
		 return this.updated_at; } 
    public void setUpdated_at(String updated_at) { 
		 this.updated_at = updated_at; } 
    String updated_at;
}
