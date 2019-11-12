package me.june;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemberTest {

    @Test
    public void getterSetter () {
        Member member = new Member();
        member.setName("june0");
        Assert.assertEquals(member.getName(), "june0");
    }

}
