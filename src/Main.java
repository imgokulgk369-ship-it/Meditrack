import model.Medicine;
import service.MedicineService;

public class Main {

    public static void main(String[] args) {

        Medicine medicine = new Medicine(
                "Paracetamol",
                "ABC Pharma",
                "Tablet",
                "B1001",
                "2027-12-31",
                100,
                12.50
        );

        MedicineService service = new MedicineService();
        service.addMedicine(medicine);

    }
}