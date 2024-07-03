package com.sparta.crunchy_crew;

import com.sparta.crunchy_crew.data_parsing.EmployeeParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class EmployeeParserTests {

    static Stream<String> getInvalidEmployeeEntries() {
        return Stream.of(
                "38234,Drs.,Gillian,T,Winter,F,gillian.winter@gmail.com,01/17/1960,11/28/1984,103619",
                "382342,Drrrs.,Gillian,T,Winter,F,gillian.winter@gmail.com,01/17/1960,11/28/1984,103619",
                "382342,Drs.,,T,Winter,F,gillian.winter@gmail.com,01/17/1960,11/28/1984,103619",
                "382342,Drs.,Gillian,,Winter,F,gillian.winter@gmail.com,01/17/1960,11/28/1984,103619",
                "382342,Drs.,Gillian,T,,F,gillian.winter@gmail.com,01/17/1960,11/28/1984,103619",
                "382342,Drs.,Gillian,T,Winter,,gillian.winter@gmail.com,01/17/1960,11/28/1984,103619",
                "382342,Drs.,Gillian,T,Winter,F,gilli@n.winter@gmail.cooom,01/17/1960,11/28/1984,103619",
                "382342,Drs.,Gillian,T,Winter,F,gillian.winter@gmail.com,1/17/1960,11/28/1984,103619",
                "382342,Drs.,Gillian,T,Winter,F,gillian.winter@gmail.com,01/17/1960,11/8/1984,103619",
                "382342,Drs.,Gillian,T,Winter,F,gillian.winter@gmail.com,01/17/1960,11/28/1984,-103619"
        );
    }

    @ParameterizedTest
    @MethodSource("getInvalidEmployeeEntries")
    void givenAStringContainingAnInvalidEmployeeEntryReturnOne(String input) {
        Assertions.assertEquals(1, EmployeeParser.parseEmployeeData(input));
    }


    static Stream<String> getValidEmployeeEntries() {
        return Stream.of(
                "301629,Mr.,Ruben,L,Weissman,M,ruben.weissman@gmail.com,12/28/1995,01/03/2017,48543",
                "668065,Mrs.,Deja,H,Niemeyer,F,deja.niemeyer@gmail.com,09/04/1961,06/05/2012,130000",
                "836931,Ms.,Selene,S,Ford,F,selene.ford@aol.com,03/23/1992,10/07/2013,143902",
                "914698,Ms.,Janeen,M,Norman,F,janeen.norman@gmail.com,01/28/1989,01/05/2012,117383",
                "872750,Mr.,Johnnie,J,Ibarra,M,johnnie.ibarra@aol.com,01/10/1972,04/14/2013,181385",
                "813428,Mr.,Emerson,L,Sands,M,emerson.sands@gmail.com,07/08/1978,01/01/2015,106048",
                "380228,Mr.,Oswaldo,V,Dodd,M,oswaldo.dodd@gmail.com,11/24/1975,11/14/1998,92047",
                "791100,Ms.,Leone,Y,Buss,F,leone.buss@yahoo.co.uk,01/31/1968,12/31/1996,44080",
                "613551,Mr.,Jay,Y,Shields,M,jay.shields@gmail.com,03/11/1981,08/26/2013,167893"
        );
    }

    @ParameterizedTest
    @MethodSource("getValidEmployeeEntries")
    void givenAStringContainingAValidEmployeeEntryReturnZero(String input) {
        Assertions.assertEquals(0, EmployeeParser.parseEmployeeData(input));
    }
}
