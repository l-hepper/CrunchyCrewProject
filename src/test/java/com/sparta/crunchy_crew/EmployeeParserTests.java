package com.sparta.crunchy_crew;

import com.sparta.crunchy_crew.data_parsing.EmployeeParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeParserTests {

    static Stream<String> getEmptyOrNullStrings() {
        return Stream.of(
                "",
                null
        );
    }

    static Stream<String> getInvalidEmployeeEntries() {
        return Stream.of(
                "38234,Drs.,Gillian,T,Winter,F,gillian.winter@gmail.com,01/17/1960,11/28/1984,103619",
                "382341,Drrrs.,Gillian,T,Winter,F,gillian.winter@gmail.com,1/17/1960,1/28/1984,103619",
                "382342,Drs.,,T,Winter,F,gillian.winter@gmail.com,01/17/1960,11/28/1984,103619",
                "382343,Drs.,Gillian,,Winter,F,gillian.winter@gmail.com,01/17/1960,11/28/1984,103619",
                "382344,Drs.,Gillian,T,,F,gillian.winter@gmail.com,01/17/1960,11/28/1984,103619",
                "382345,Drs.,Gillian,T,Winter,,gillian.winter@gmail.com,01/17/1960,11/28/1984,103619",
                "382346,Drs.,Gillian,T,Winter,F,gilli@n.winter@gmail.cooom,01/17/1960,11/28/1984,103619",
                "382347,Drs.,Gillian,T,Winter,F,gillian.winter@gmail.com,01/17/1960,11/28/1984,-103619",
                "382348,,,,,,,,,",
                "",
                null
        );
    }

    @ParameterizedTest
    @MethodSource("getInvalidEmployeeEntries")
    void givenAStringContainingAnInvalidEmployeeEntryReturnOne(String input) {
        boolean invalidEntry = EmployeeParser.parseEmployeeData(input).isEmpty();
        Assertions.assertTrue(invalidEntry);
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
        boolean validEntry = EmployeeParser.parseEmployeeData(input).isPresent();
        Assertions.assertTrue(validEntry);
    }

    @Test
    public void givenValidEmployeeIDReturnsString() {
        String empId = "123456";
        assertEquals(empId, EmployeeParser.parseEmpId(empId));
    }

    @Test
    public void givenInvalidEmployeeIDThrowsException() {
        String empId = "asd434123n222";
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseEmpId(empId));
    }

    @ParameterizedTest
    @MethodSource("getEmptyOrNullStrings")
    public void givenEmptyOrNullEmployeeIDThrowsException(String empId) {
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseEmpId(empId));
    }

    @Test
    public void givenValidPrefixReturnsString() {
        String prefix = "Mr.";
        assertEquals(prefix, EmployeeParser.parsePrefix(prefix));
    }

    @Test
    public void givenInvalidPrefixNoDotReturnsThrowsException() {
        String prefix = "Mrs";
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parsePrefix(prefix));
    }

    @Test
    public void givenInvalidPrefixLessThanThreeReturnsThrowsException() {
        String prefix = "Mr";
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parsePrefix(prefix));
    }

    @Test
    public void givenInvalidPrefixGreaterThanFiveThrowsException() {
        String prefix = "Proff.";
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parsePrefix(prefix));
    }

    @ParameterizedTest
    @MethodSource("getEmptyOrNullStrings")
    public void givenEmptyOrNullPrefixThrowsException(String prefix) {
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parsePrefix(prefix));
    }

    @ParameterizedTest
    @MethodSource("getEmptyOrNullStrings")
    public void givenEmptyOrNullFirstNameThrowsException(String name) {
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseFirstName(name));
    }

    @Test
    public void givenInvalidMiddleInitialThrowsException() {
        String initial = "JJ";
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseMiddleInitial(initial));
    }

    @ParameterizedTest
    @MethodSource("getEmptyOrNullStrings")
    public void givenEmptyOrNullMiddleInitialThrowsException(String initial) {
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseMiddleInitial(initial));
    }

    @ParameterizedTest
    @MethodSource("getEmptyOrNullStrings")
    public void givenEmptyOrNullLastNameThrowsException(String name) {
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseLastName(name));
    }

    @Test
    public void givenValidGenderReturnChar() {
        String gender = "F";
        assertEquals('F', EmployeeParser.parseGender(gender));
    }

    @Test
    public void givenInvalidGenderThrowsException() {
        String gender = "P";
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseGender(gender));
    }

    @ParameterizedTest
    @MethodSource("getEmptyOrNullStrings")
    public void givenEmptyOrNullGenderThrowsException(String gender) {
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseGender(gender));
    }

    @Test
    public void givenValidEmailReturnString() {
        String email = "username@domain.co.uk";
        assertEquals(email, EmployeeParser.parseEmail(email));
    }

    @Test
    public void givenInvalidEmailTwoAtSignsThrowsException() {
        String email = "usern@me@email.gov";
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseEmail(email));
    }

    @Test
    public void givenInvalidEmailDoubleDotsThrowsException() {
        String email = "user..name@email.gov";
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseEmail(email));
    }

    @Test
    public void givenInvalidEmailEndsWithDotThrowsException() {
        String email = "username@domain.com.";
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseEmail(email));
    }

    @ParameterizedTest
    @MethodSource("getEmptyOrNullStrings")
    public void givenEmptyOrNullEmailThrowsException(String email) {
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseEmail(email));
    }

    @Test
    public void givenValidBirthdayReturnLocalDate() {
        String birthday = "1/1/1990";
        assertEquals(LocalDate.of(1990, 1, 1), EmployeeParser.parseBirthday(birthday));
    }

    @Test
    public void givenInvalidBirthdayWrongFormatThrowException() {
        String birthday = "1990/01/01";
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseBirthday(birthday));
    }

    @Test
    public void givenInvalidBirthdayTooYoungThrowException() {
        String birthday = "04/07/2020";
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseBirthday(birthday));
    }

    @Test
    public void givenInvalidBirthdayTooOldThrowException() {
        String birthday = "04/07/1920";
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseBirthday(birthday));
    }

    @Test
    public void givenInvalidBirthdayInFutureThrowException() {
        String birthday = "04/07/2920";
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseBirthday(birthday));
    }

    @ParameterizedTest
    @MethodSource("getEmptyOrNullStrings")
    public void givenEmptyOrNullBirthdayThrowsException(String birthday) {
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseBirthday(birthday));
    }

    @Test
    public void givenValidStartDateReturnLocalDate() {
        String startDate = "1/1/2020";
        assertEquals(LocalDate.of(2020, 1, 1), EmployeeParser.parseStartDate(startDate, LocalDate.of(2000, 1, 1)));
    }

    @Test
    public void givenInvalidStartDateWrongFormatThrowException() {
        String startDate = "2020-01-01";
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseStartDate(startDate, LocalDate.of(2000, 1, 1)));
    }

    @Test
    public void givenInvalidStartDateTooYoungThrowException() {
        String startDate = "1/1/2020";
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseStartDate(startDate, LocalDate.of(2005, 1, 1)));
    }

    @Test
    public void givenInvalidStartDateInFutureThrowException() {
        String startDate = "1/1/3020";
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseStartDate(startDate, LocalDate.of(2000, 1, 1)));
    }

    @ParameterizedTest
    @MethodSource("getEmptyOrNullStrings")
    public void givenEmptyOrNullStartDateThrowsException(String startDate) {
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseStartDate(startDate,  LocalDate.of(2000, 1, 1)));
    }

    @ParameterizedTest
    @MethodSource("getEmptyOrNullStrings")
    public void givenEmptyOrNullBirthdayForStartDateThrowsException(String birthday) {
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseStartDate("1/1/2020",  null));
    }

    @ParameterizedTest
    @MethodSource("getEmptyOrNullStrings")
    public void givenEmptyOrNullStartDateAndBirthdayThrowsException(String startDate) {
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseStartDate(startDate, null));
    }

    @Test
    public void givenValidSalaryReturnInt() {
        String salary = "50000";
        assertEquals(50000, EmployeeParser.parseSalary(salary));
    }

    @Test
    public void givenInvalidSalaryThrowException() {
        String salary = "-50000";
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseSalary(salary));
    }

    @ParameterizedTest
    @MethodSource("getEmptyOrNullStrings")
    public void givenEmptyOrNullSalaryThrowsException(String salary) {
        assertThrowsExactly(IllegalArgumentException.class,() -> EmployeeParser.parseSalary(salary));
    }
}
