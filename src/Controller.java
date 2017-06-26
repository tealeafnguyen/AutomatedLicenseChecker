
public class Controller {

	public static void main(String[] args) {

		if(args.length <= 0){
			System.out.println("No file name provided");
			System.exit(0);
		}
		else{
			String fileName = args[0];
			System.out.println(fileName);
		}

		//		DayCounter dc = new DayCounter();
		//		dc.count();
		//		EmailSender es = new EmailSender();
		//		es.send("test");

	}

}
