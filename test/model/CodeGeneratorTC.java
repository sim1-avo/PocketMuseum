package model;

import model.biglietto.CodeGenerator;
import org.junit.jupiter.api.Test;

public class CodeGeneratorTC {

  @Test
  public void Test1() {
    CodeGenerator cd = new CodeGenerator(1);
    cd.nextCode();
  }

  @Test
  public void Test2() {
    CodeGenerator cd1 = new CodeGenerator(11);
    cd1.nextCode();
  }

  @Test
  public void Test3() {
    CodeGenerator cd2 = new CodeGenerator(111);
    cd2.nextCode();
  }

  @Test
  public void Test4() {
    CodeGenerator cd3 = new CodeGenerator(1111);
    cd3.nextCode();
  }

  @Test
  public void Test5() {
    CodeGenerator cd4 = new CodeGenerator(11111);
    cd4.nextCode();
  }
}
