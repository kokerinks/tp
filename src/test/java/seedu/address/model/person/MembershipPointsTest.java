package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MembershipPointsTest {

    @Test
    public void getTier_test() {
        MembershipPoints points = new MembershipPoints(0);
        assertEquals(points.getTier(), "BRONZE");
        points = new MembershipPoints(2500);
        assertEquals(points.getTier(), "SILVER");
        points = new MembershipPoints(5000);
        assertEquals(points.getTier(), "GOLD");
        points = new MembershipPoints(12000);
        assertEquals(points.getTier(), "PLATINUM");
    }
}
