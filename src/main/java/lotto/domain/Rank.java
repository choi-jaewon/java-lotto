package lotto.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Rank {
    FIFTH(5_000, 3, false),
    FORTH(50_000, 4, false),
    THIRD(1_500_000, 5, false),
    SECOND(30_000_000, 5, true),
    FIRST(2_000_000_000, 6, false),
    NO_LUCK(0, 0, false);

    private final int money;
    private final int matchingCount;
    private final boolean bonusNumber;

    Rank(int money, int matchingCount, boolean bonusNumber) {
        this.money = money;
        this.matchingCount = matchingCount;
        this.bonusNumber = bonusNumber;
    }

    public int getMoney() {
        return money;
    }

    /**
     * 당천된 숫자의 개수와 보너스 번호의 일치여부를 확인해 해당하는 등수를 반환하는 메소드
     * @param matchingCount
     * @param bonusNumber
     * @return
     */
    public static Rank of(int matchingCount, boolean bonusNumber) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchingCount == matchingCount && rank.bonusNumber == bonusNumber)
                .findAny()
                .orElse(NO_LUCK);
    }

    /**
     * 등수 Map 에서 기본 개수를 적용하기 위한 메소드
     * @return
     */
    private int defaultCount() {
        return 0;
    }

    /**
     * 등수가 key, 개수가 0인 value 로 이루어진 기본 Map 을 반환하는 메소드
     * @return
     */
    public static Map<Rank, Integer> getRankWithCount() {
        return Stream.of(Rank.values()).collect(Collectors.toMap(Function.identity(), Rank::defaultCount));
    }
}