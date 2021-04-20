package tech.qvanphong.arkdc.models; 
import com.fasterxml.jackson.annotation.JsonProperty; 
public class Datum{
    @JsonProperty("id") 
    public int getId() { 
		 return this.id; } 
    public void setId(int id) { 
		 this.id = id; } 
    int id;
    @JsonProperty("name") 
    public String getName() { 
		 return this.name; } 
    public void setName(String name) { 
		 this.name = name; } 
    String name;
    @JsonProperty("slug") 
    public String getSlug() { 
		 return this.slug; } 
    public void setSlug(String slug) { 
		 this.slug = slug; } 
    String slug;
    @JsonProperty("rank") 
    public int getRank() { 
		 return this.rank; } 
    public void setRank(int rank) { 
		 this.rank = rank; } 
    int rank;
    @JsonProperty("address") 
    public String getAddress() { 
		 return this.address; } 
    public void setAddress(String address) { 
		 this.address = address; } 
    String address;
    @JsonProperty("public_key") 
    public String getPublic_key() { 
		 return this.public_key; } 
    public void setPublic_key(String public_key) { 
		 this.public_key = public_key; } 
    String public_key;
    @JsonProperty("website") 
    public String getWebsite() { 
		 return this.website; } 
    public void setWebsite(String website) { 
		 this.website = website; } 
    String website;
    @JsonProperty("proposal") 
    public String getProposal() { 
		 return this.proposal; } 
    public void setProposal(String proposal) { 
		 this.proposal = proposal; } 
    String proposal;
    @JsonProperty("is_private") 
    public boolean getIs_private() { 
		 return this.is_private; } 
    public void setIs_private(boolean is_private) { 
		 this.is_private = is_private; } 
    boolean is_private;
    @JsonProperty("payout_covering_fee") 
    public boolean getPayout_covering_fee() { 
		 return this.payout_covering_fee; } 
    public void setPayout_covering_fee(boolean payout_covering_fee) { 
		 this.payout_covering_fee = payout_covering_fee; } 
    boolean payout_covering_fee;
    @JsonProperty("payout_percent") 
    public int getPayout_percent() { 
		 return this.payout_percent; } 
    public void setPayout_percent(int payout_percent) { 
		 this.payout_percent = payout_percent; } 
    int payout_percent;
    @JsonProperty("payout_interval") 
    public int getPayout_interval() { 
		 return this.payout_interval; } 
    public void setPayout_interval(int payout_interval) { 
		 this.payout_interval = payout_interval; } 
    int payout_interval;
    @JsonProperty("payout_minimum") 
    public String getPayout_minimum() { 
		 return this.payout_minimum; } 
    public void setPayout_minimum(String payout_minimum) { 
		 this.payout_minimum = payout_minimum; } 
    String payout_minimum;
    @JsonProperty("payout_maximum") 
    public String getPayout_maximum() { 
		 return this.payout_maximum; } 
    public void setPayout_maximum(String payout_maximum) { 
		 this.payout_maximum = payout_maximum; } 
    String payout_maximum;
    @JsonProperty("payout_minimum_vote_amount") 
    public String getPayout_minimum_vote_amount() { 
		 return this.payout_minimum_vote_amount; } 
    public void setPayout_minimum_vote_amount(String payout_minimum_vote_amount) { 
		 this.payout_minimum_vote_amount = payout_minimum_vote_amount; } 
    String payout_minimum_vote_amount;
    @JsonProperty("payout_maximum_vote_amount") 
    public String getPayout_maximum_vote_amount() { 
		 return this.payout_maximum_vote_amount; } 
    public void setPayout_maximum_vote_amount(String payout_maximum_vote_amount) { 
		 this.payout_maximum_vote_amount = payout_maximum_vote_amount; } 
    String payout_maximum_vote_amount;
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
    @JsonProperty("delegateStatistics") 
    public DelegateStatistics getDelegateStatistics() { 
		 return this.delegateStatistics; } 
    public void setDelegateStatistics(DelegateStatistics delegateStatistics) { 
		 this.delegateStatistics = delegateStatistics; } 
    DelegateStatistics delegateStatistics;
    @JsonProperty("contributionsCount") 
    public int getContributionsCount() { 
		 return this.contributionsCount; } 
    public void setContributionsCount(int contributionsCount) { 
		 this.contributionsCount = contributionsCount; } 
    int contributionsCount;
    @JsonProperty("is_claimed") 
    public boolean getIs_claimed() { 
		 return this.is_claimed; } 
    public void setIs_claimed(boolean is_claimed) { 
		 this.is_claimed = is_claimed; } 
    boolean is_claimed;
    @JsonProperty("days_since_last_contribution") 
    public int getDays_since_last_contribution() { 
		 return this.days_since_last_contribution; } 
    public void setDays_since_last_contribution(int days_since_last_contribution) { 
		 this.days_since_last_contribution = days_since_last_contribution; } 
    int days_since_last_contribution;
    @JsonProperty("contribution_status") 
    public String getContribution_status() { 
		 return this.contribution_status; } 
    public void setContribution_status(String contribution_status) { 
		 this.contribution_status = contribution_status; } 
    String contribution_status;
}
