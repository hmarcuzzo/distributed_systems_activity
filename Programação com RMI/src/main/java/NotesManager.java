/**
 * Implementacao do objeto remoto
 * autor: Rodrigo Campiolo
 * data: 22/11/2006
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class NotesManager extends UnicastRemoteObject implements NotesManagerRMI {

    public NotesManager() throws RemoteException {
        super();
        System.out.println("Instance of Object created with success");
    } //Calc

    public Response add_grade(String RA, String disciplineCode, Integer disciplineYear, Integer disciplineSemester, Float grade, Integer absences) throws RemoteException {

        String search_aluno_query = "SELECT * FROM aluno WHERE (ra = " + String.valueOf(RA) + ");";
        String search_discipline_query = "SELECT * FROM disciplina WHERE (codigo = '" + String.valueOf(disciplineCode)
            + "');";
        String search_matricula_query = "SELECT * FROM matricula WHERE (ra_aluno = " + String.valueOf(RA)
            + " AND cod_disciplina = '" + String.valueOf(disciplineCode) + "' AND ano = " + String.valueOf(disciplineYear)
            + " AND semestre = " + String.valueOf(disciplineSemester) + ");";
        String update_grade_query = "UPDATE matricula SET grade = " + String.valueOf(grade) + ", faltas = "
            + String.valueOf(absences) + " WHERE (ra_aluno = " + String.valueOf(RA) + " AND cod_disciplina = '"
            + String.valueOf(disciplineCode) + "' AND ano = " + String.valueOf(disciplineYear) + " AND semestre = "
            + String.valueOf(disciplineSemester) + ");";
        String create_matricula = "INSERT INTO matricula (ano, semestre, cod_disciplina, ra_aluno, nota, faltas) VALUES ("
        + disciplineYear + ", " + disciplineSemester + ", '" + disciplineCode + "', " + RA + ", " + grade + ", " + absences + ");"; 


        Connection db_connection = SQLiteConnection.connect();    
        Statement statement = db_connection.createStatement();
        try {


            /* search for aluno */
            ResultSet resultSet = statement.executeQuery(search_aluno_query);
            if (!resultSet.isBeforeFirst()) {
                            /* nao achou */

            }

            /* search for disiplina */
            resultSet = statement.executeQuery(search_discipline_query);
            if (!resultSet.isBeforeFirst()) {
            }

            /* search for matricula */
            resultSet = statement.executeQuery(search_matricula_query);

            if (!resultSet.isBeforeFirst()) {

                statement.execute(create_matricula);
                /* search for disiplina */
                ResultSet resultSet2 = statement.executeQuery(search_discipline_query);
                if (!resultSet2.isBeforeFirst()) {
                }
                 
            } else {
                /* Atualiza grade */
                statement.execute(update_grade_query);
                 
            }

            Response resp = new Response(1);
            return resp.get();

        } catch (SQLException e) {
            Response resp = new Response(0);
            return resp.get();
        }
    } //add_grade

    public Response remove_grade (String RA, String disciplineCode, Integer disciplineYear, Integer disciplineSemester) throws RemoteException {
        /* GET QUERYS */
        String search_aluno_query = "SELECT * FROM aluno WHERE (ra = " + String.valueOf(RA) + ");";
        String search_discipline_query = "SELECT * FROM disciplina WHERE (codigo = '" + String.valueOf(disciplineCode)
            + "');";
        String search_matricula_query = "SELECT * FROM matricula WHERE (ra_aluno = " + String.valueOf(RA)
            + " AND cod_disciplina = '" + String.valueOf(disciplineCode) + "' AND ano = " + String.valueOf(disciplineYear)
            + " AND semestre = " + String.valueOf(disciplineSemester) + ");";
        String remove_nota_query = "UPDATE matricula SET nota = '' WHERE (ra_aluno = " + String.valueOf(RA)
            + " AND cod_disciplina = '" + String.valueOf(disciplineCode) + "' AND ano = " + String.valueOf(disciplineYear)
            + " AND semestre = " + String.valueOf(disciplineSemester) + ");";

        Connection db_connection = SQLiteConnection.connect();    
        Statement statement = db_connection.createStatement();
        try {
    

            /* search for aluno */
            ResultSet resultSet = statement.executeQuery(search_aluno_query);
            if (!resultSet.isBeforeFirst()) {
                //response.setResponse("RA inexistente");
                Response resp = new Response(0);
                return resp.get();
            }

            /* search for disiplina */
            resultSet = statement.executeQuery(search_discipline_query);
            if (!resultSet.isBeforeFirst()) {
                //response.setResponse("Disciplina inexistente");
                Response resp = new Response(0);
                return resp.get();
            }

            /* search for matricula */
            resultSet = statement.executeQuery(search_matricula_query);
            if (!resultSet.isBeforeFirst()) {
                Response resp = new Response(0);
                return resp.get();
            }

            /* remove nota */
            statement.execute(remove_nota_query);
            Response resp = new Response(1);
            return resp.get();

        } catch (SQLException e) {
            statement.execute(remove_nota_query);
            Response resp = new Response(1);
        }
        Response resp = new Response(1);
        return resp.get();
    } //remove_grade


    public Response get_grades_by_aluno (String RA) throws RemoteException {
         Response resp = new Response(1);
        return resp.get();
    } // divide

    public Response list_alunos (String disciplineCode, Integer disciplineYear, Integer disciplineSemester) throws RemoteException {
        
        String disciplina_query = "SELECT * FROM disciplina WHERE (codigo = '" + String.valueOf(disciplineCode) + "');";
        String get_alunos_query = "SELECT *  FROM aluno as A JOIN matricula AS M ON A.RA = M.ra_aluno WHERE (M.ano = " + String.valueOf(disciplineYear) + " AND M.semestre = " + String.valueOf(disciplineSemester) + " AND M.cod_disciplina = '" + String.valueOf(disciplineCode) + "');";
        
        Connection db_connection = SQLiteConnection.connect(); 
        Statement statement = db_connection.createStatement();
        try {

            /* search for disiplina */
            ResultSet resultSet = statement.executeQuery(disciplina_query);
            if (!resultSet.isBeforeFirst()) {
            /* n existe*/
            }

            /* Lista alunos */
            resultSet = statement.executeQuery(get_alunos_query);
            if (!resultSet.isBeforeFirst()) {
              
            }

            while (resultSet.next()) {

                /* Construindo Matricula */
                /* Adicionando matricula */
                  
            }
            
             

        } catch (SQLException e) {
            Response resp = new Response(0);
            return resp.get();
        }
        Response resp = new Response(1);
        return resp.get();
    } //multiplica

    public Response list_grades (String disciplineCode, Integer disciplineYear, Integer disciplineSemester) throws RemoteException{
         Response resp = new Response(1);
        return resp.get();
    }

} //get_grades_by_aluno
