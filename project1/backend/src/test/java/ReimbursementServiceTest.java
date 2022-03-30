import com.revature.dao.ReimbursementDao;
import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.ResponseReimbursementDTO;
import com.revature.exception.InvalidImageException;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.ReimbursementService;
import io.javalin.http.UploadedFile;
import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //public class
public class ReimbursementServiceTest {
    @Mock
    ReimbursementDao reimbursementDao;



    @InjectMocks
    ReimbursementService reimbursementService;


    @Test
    public void testGetAllReimbursements() throws SQLException {
        // Arrange
        User employee = new User(1000, "test", "test","test","test","test","employee");
        User manager = new User(1000, "test", "test","test","test","test","manager");

        List<Reimbursement> fakeReimbursements = new ArrayList<>();
        fakeReimbursements.add(new Reimbursement(1, 200, "Smith", null,"description",1,2,1,2,employee,manager));
        fakeReimbursements.add(new Reimbursement(1, 200, "Smith", null,"description",1,2,2,2,employee,manager));
        fakeReimbursements.add(new Reimbursement(1, 200, "Smith", null,"description",1,2,3,2,employee,manager));

        // Whenever the code in the Service layer calls the getAllStudents() method
        // for the dao layer, then return the list of students
        // we have specified above
        when(reimbursementDao.getAllReimbursements()).thenReturn(fakeReimbursements);
        List<ResponseReimbursementDTO> fakeReimbursementDTOs = new ArrayList<>();
        fakeReimbursementDTOs.add(new ResponseReimbursementDTO(1, 200, "Smith", null,"description",1,1,2,"test","test"));
        fakeReimbursementDTOs.add(new ResponseReimbursementDTO(1, 200, "Smith", null,"description",1,2,2,"test","test"));
        fakeReimbursementDTOs.add(new ResponseReimbursementDTO(1, 200, "Smith", null,"description",1,3,2,"test","test"));


        // Act
        List<ResponseReimbursementDTO> actual = reimbursementService.getAllReimbursements();

        // Assert
        List<ResponseReimbursementDTO> expected = new ArrayList<>(fakeReimbursementDTOs);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void testGetAllApprovedReimbursements() throws SQLException {
        // Arrange
        User employee = new User(1000, "test", "test","test","test","test","employee");
        User manager = new User(1000, "test", "test","test","test","test","manager");

        List<Reimbursement> fakeReimbursements = new ArrayList<>();
        fakeReimbursements.add(new Reimbursement(1, 200, "Smith", null,"description",1,2,1,2,employee,manager));

        // Whenever the code in the Service layer calls the getAllStudents() method
        // for the dao layer, then return the list of students
        // we have specified above
        when(reimbursementDao.getAllApprovedReimbursements()).thenReturn(fakeReimbursements);
        List<ResponseReimbursementDTO> fakeReimbursementDTOs = new ArrayList<>();
        fakeReimbursementDTOs.add(new ResponseReimbursementDTO(1, 200, "Smith", null,"description",1,1,2,"test","test"));

        // Act
        List<ResponseReimbursementDTO> actual = reimbursementService.getAllApprovedReimbursements();

        // Assert
        List<ResponseReimbursementDTO> expected = new ArrayList<>(fakeReimbursementDTOs);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void testGetAllPendingReimbursements() throws SQLException {
        // Arrange
        User employee = new User(1000, "test", "test","test","test","test","employee");
        User manager = new User(1000, "test", "test","test","test","test","manager");

        List<Reimbursement> fakeReimbursements = new ArrayList<>();
        fakeReimbursements.add(new Reimbursement(1, 200, "Smith", null,"description",1,2,2,2,employee,manager));

        // Whenever the code in the Service layer calls the getAllStudents() method
        // for the dao layer, then return the list of students
        // we have specified above
        when(reimbursementDao.getAllPendingReimbursements()).thenReturn(fakeReimbursements);
        List<ResponseReimbursementDTO> fakeReimbursementDTOs = new ArrayList<>();
        fakeReimbursementDTOs.add(new ResponseReimbursementDTO(1, 200, "Smith", null,"description",1,2,2,"test","test"));

        // Act
        List<ResponseReimbursementDTO> actual = reimbursementService.getAllPendingReimbursements();

        // Assert
        List<ResponseReimbursementDTO> expected = new ArrayList<>(fakeReimbursementDTOs);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void testGetAllDeniedReimbursements() throws SQLException {
        // Arrange
        User employee = new User(1000, "test", "test","test","test","test","employee");
        User manager = new User(1000, "test", "test","test","test","test","manager");

        List<Reimbursement> fakeReimbursements = new ArrayList<>();
        fakeReimbursements.add(new Reimbursement(1, 200, "Smith", null,"description",1,2,3,2,employee,manager));

        // Whenever the code in the Service layer calls the getAllStudents() method
        // for the dao layer, then return the list of students
        // we have specified above
        when(reimbursementDao.getAllDeniedReimbursements()).thenReturn(fakeReimbursements);
        List<ResponseReimbursementDTO> fakeReimbursementDTOs = new ArrayList<>();
        fakeReimbursementDTOs.add(new ResponseReimbursementDTO(1, 200, "Smith", null,"description",1,3,2,"test","test"));

        // Act
        List<ResponseReimbursementDTO> actual = reimbursementService.getAllDeniedReimbursements();

        // Assert
        List<ResponseReimbursementDTO> expected = new ArrayList<>(fakeReimbursementDTOs);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void testGetReimbursementsByEmployee() throws SQLException {
        // Arrange
        User employee = new User(1, "test", "test","test","test","test","employee");
        User manager = new User(1, "test", "test","test","test","test","manager");

        List<Reimbursement> fakeReimbursements = new ArrayList<>();
        fakeReimbursements.add(new Reimbursement(1, 200, "Smith", null,"description",1,2,3,2,employee,manager));

        // Whenever the code in the Service layer calls the getAllStudents() method
        // for the dao layer, then return the list of students
        // we have specified above
        when(reimbursementDao.getReimbursementsByEmployee(1)).thenReturn(fakeReimbursements);
        List<ResponseReimbursementDTO> fakeReimbursementDTOs = new ArrayList<>();
        fakeReimbursementDTOs.add(new ResponseReimbursementDTO(1, 200, "Smith", null,"description",1,3,2,"test","test"));

        // Act
        List<ResponseReimbursementDTO> actual = reimbursementService.getReimbursementsByEmployee(1);

        // Assert
        List<ResponseReimbursementDTO> expected = new ArrayList<>(fakeReimbursementDTOs);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetPendingReimbursementsByEmployee() throws SQLException {
        // Arrange
        User employee = new User(1, "test", "test","test","test","test","employee");
        User manager = new User(1, "test", "test","test","test","test","manager");

        List<Reimbursement> fakeReimbursements = new ArrayList<>();
        fakeReimbursements.add(new Reimbursement(1, 200, "Smith", null,"description",1,2,3,2,employee,manager));

        // Whenever the code in the Service layer calls the getAllStudents() method
        // for the dao layer, then return the list of students
        // we have specified above
        when(reimbursementDao.getPendingReimbursementsByEmployee(1)).thenReturn(fakeReimbursements);
        List<ResponseReimbursementDTO> fakeReimbursementDTOs = new ArrayList<>();
        fakeReimbursementDTOs.add(new ResponseReimbursementDTO(1, 200, "Smith", null,"description",1,3,2,"test","test"));

        // Act
        List<ResponseReimbursementDTO> actual = reimbursementService.getPendingReimbursementsByEmployee(1);

        // Assert
        List<ResponseReimbursementDTO> expected = new ArrayList<>(fakeReimbursementDTOs);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetResolvedReimbursementsByEmployee() throws SQLException {
        // Arrange
        User employee = new User(1, "test", "test","test","test","test","employee");
        User manager = new User(1, "test", "test","test","test","test","manager");

        List<Reimbursement> fakeReimbursements = new ArrayList<>();
        fakeReimbursements.add(new Reimbursement(1, 200, "Smith", null,"description",1,2,1,2,employee,manager));

        // Whenever the code in the Service layer calls the getAllStudents() method
        // for the dao layer, then return the list of students
        // we have specified above
        when(reimbursementDao.getResolvedReimbursementsByEmployee(1)).thenReturn(fakeReimbursements);
        List<ResponseReimbursementDTO> fakeReimbursementDTOs = new ArrayList<>();
        fakeReimbursementDTOs.add(new ResponseReimbursementDTO(1, 200, "Smith", null,"description",1,1,2,"test","test"));

        // Act
        List<ResponseReimbursementDTO> actual = reimbursementService.getResolvedReimbursementsByEmployee(1);

        // Assert
        List<ResponseReimbursementDTO> expected = new ArrayList<>(fakeReimbursementDTOs);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void testaddReimbursement() throws InvalidImageException, IOException, SQLException {
        User employee = new User(1, "test", "test","test","test","test","employee");
        User manager = new User(1, "test", "test","test","test","test","manager");


        InputStream image = new FileInputStream("C://Users/jackz/pictures/dreamlair.png");
        when(reimbursementDao.addReimbursement(1,new AddReimbursementDTO(1, "test", "Smith",image,1))).thenReturn(new Reimbursement(1,1,"test","test","test",1,1,1,1,employee,null));


        //List<AddReimbursementDTO> actual = (List<AddReimbursementDTO>) reimbursementService.addReimbursement(1,new AddReimbursementDTO(1, "test", "Smith",stubInputStream,1));

        ResponseReimbursementDTO actual = reimbursementService.addReimbursement(1,new AddReimbursementDTO(1, "test", "Smith",image,1));

        ResponseReimbursementDTO expected = new ResponseReimbursementDTO(1, 1,"test",null,"test",1,1, 1,"test",null);

        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testJudgeReimbursement() throws SQLException {
        User employee = new User(1, "test", "test","test","test","test","employee");
        User manager = new User(1, "test", "test","test","test","test","manager");
        when(reimbursementDao.judgeReimbursement(1,2,"test",1)).thenReturn(new Reimbursement(1, 1, "test", null,"test",1,2,1,1,employee,manager));


        ResponseReimbursementDTO actual = reimbursementService.judgeReimbursement("1","2","test",1);


        ResponseReimbursementDTO expected = new ResponseReimbursementDTO(1, 1,"test",null,"test",1,1, 1,"test","test");
        Assertions.assertEquals(expected,actual);



    }

    @Test
    public void testGetImage() throws SQLException, FileNotFoundException {
        InputStream image = new FileInputStream("C://Users/jackz/pictures/dreamlair.png");

        when(reimbursementDao.getReimbursementImage(1)).thenReturn(image);

        InputStream actual = reimbursementService.getReimbursementImage(String.valueOf(1));

        InputStream expected = image;

        Assertions.assertEquals(expected,actual);
    }
}


