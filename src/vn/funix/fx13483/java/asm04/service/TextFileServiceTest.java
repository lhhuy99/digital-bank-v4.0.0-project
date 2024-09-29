package vn.funix.fx13483.java.asm04.service;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TextFileServiceTest {

    @Test
    public void readFile() {
        List<List<String>> listList = new ArrayList<>();
        assertEquals(listList, TextFileService.readFile("abc.txt"));
        List<String> list = new ArrayList<>(Arrays.asList("001215000001","Le Van A"));
        listList.add(list);
        assertEquals(listList, TextFileService.readFile("ReadFileTest.txt"));
    }
}