package tech.qvanphong.arkdc.utils;

public class StakeCalculator {

    public static double calculateStake(Double balance, int payoutPercent, String votingPower, boolean isVoted) {
        double arkShare = 422 * payoutPercent / 100D;
        long convertedVotingWeightNumber = votingPower == null || votingPower.isEmpty() ? 0 : Long.parseLong(votingPower);
        double votingWeight = Math.round(convertedVotingWeightNumber / 100000000F);
        double arkShareByDelegate = (votingWeight + (isVoted ? 0 : balance)) / balance;

        return arkShare / arkShareByDelegate;
    }

    public static double calculateStakeByWeek(Double balance, int payoutPercent, String votingPower, boolean isVoted) {
        return calculateStake(balance, payoutPercent, votingPower, isVoted) * 7;
    }

    public static double calculateStakeByMonth(Double balance, int payoutPercent, String votingPower, boolean isVoted) {
        return calculateStake(balance, payoutPercent, votingPower, isVoted) * 30;
    }

    public static double calculateStakeByYear(Double balance, int payoutPercent, String votingPower, boolean isVoted) {
        return calculateStake(balance, payoutPercent, votingPower, isVoted) * 365;
    }
}
