/**
 * Este código é responsavel por fomalizar o modelo do aluno
 * 
 * @author Henrique Marcuzzo (@hmarcuzzo)
 * @author Rafael Soratto (@sorattorafa)
 * 
 * Data de Criação: 25 de Jul de 2021
 * Ultima alteração: 25 de Jul de 2021
 */

import java.util.ArrayList;

public class Response {
  String response;
  ArrayList<Student> students = new ArrayList<Student>();

  public void set_response(String response) {
    this.response = response;
  }

  public void set_students(Student aluno) {
    this.students.add(aluno);
  }
}