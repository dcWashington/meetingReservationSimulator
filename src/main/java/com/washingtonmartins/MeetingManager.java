package com.washingtonmartins;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("deprecation")
public class MeetingManager {

	List<Meeting> meetings = new ArrayList<Meeting>();
	Scanner input = new Scanner(System.in);

	public void start() {

		int selection;
		do {
			mainMenu();

			selection = input.nextInt();
			switch (selection) {
			case 1:
				addMeeting();
				break;

			case 2:
				displayReport();
				break;

			case 3:
				deleteById();
				break;

			case 4:
				clearMeetings();
				break;

			case 5:
				updateById();
				break;

			case 6:
				closeProgram();
				break;
			}
		} while (selection < 6);
	}

	public void closeProgram() {
		System.out.println("Good bye! \n ");
		input.close();
		System.exit(0);
	}

	public void clearMeetings() {
		System.out.println("You have deleted all the meetings! \n");
		meetings.clear();
	}

	public void addMeeting() {

		System.out.println("What's the meeting's subject?\n");
		String subject = input.next();

		System.out.println("When is the meeting's date? (mm/dd/yyyy)\\n");
		String startDay = input.next();

		System.out.println("When the starts? (hh:mm)\\n");
		String startTime = input.next();

		System.out.println("Is this meeting urgent?");
		System.out.println("0 - High priority \n" + "1 - Normal priority \n" + "2 - It's not urgent \n");
		int priority = input.nextInt();

		Meeting meetingFound = getMeetingFromList(startDay, startTime);
		
		if(meetingFound != null) {
			if(meetingFound.getPriority() == priority) {
				System.out.println("you can't do this");
			}else {
				if(priority < meetingFound.getPriority() ) {
					System.out.println("Do you want to overlap?");
					System.out.println("0 - No \n" + "1 - Yes");
					String confirmation = input.next();
					
					if(confirmation.equals("0")) {
						System.out.println("Nothing was overlaped.");
					}else {
						meetingFound.setSubject(subject);
						meetingFound.setStartDay(new Date(startDay + " " + startTime));
						meetingFound.setPriority(priority);
						System.out.println("Meeting updated! \n ");
					}
				}else {
					System.out.println("you can't do this");
				}
			}
		}else {
			Meeting m = new Meeting();
			
			m.setSubject(subject);
			m.setStartDay(new Date(startDay + " " + startTime));
			m.setPriority(priority);
			
			meetings.add(m);
			displayReport();
		}

	}

	private Meeting getMeetingFromList(String startDay, String startTime) {
		for (Meeting m : meetings) {
			Date newDate = new Date(startDay + " " + startTime);
			
			if (m.getStartDay().compareTo(newDate) == 0) {
				System.out.println("This spot is already booked \n");
				return m;
			}
		}

		return null;
	}
	
	public void deleteById() {

		System.out.println("What is the Meeting Id you want to remove? \n ");

		int inputId = input.nextInt();

		if (areYouSureMenu()) {
			for (Meeting m : meetings) {
				if (inputId == m.getId()) {
					meetings.remove(m);
					System.out.println("Meeting deleted! \n");
					break;
				}
			}

		} else {
			System.out.println("Nothing was deleted! \n");
		}
		displayReport();
	}

//		public static List setOrder(List<Meeting> meetings) {
//			Collections.sort(meetings, (o1, o2) -> o1.getStartDay().compareTo(o2.getStartDay()));
//			return meetings;
//		}

	public static void displayInOrder() {

	}

	private void updateById() {
		System.out.println("What is the Meeting Id you want to update? \n ");
		int inputID = input.nextInt();

		System.out.println("What's the meeting's subject?\n");
		String subject = input.next();

		System.out.println("When is the meeting's date? (mm/dd/yyyy)\\n");
		String startDay = input.next();

		System.out.println("When the starts? (hh:mm)\\n");
		String startTime = input.next();

		System.out.println("Is this meeting urgent?");
		System.out.println("0 - Hight priority \n" + "1 - Normal priority \n" + "2 - It's not urgent \n");
		int priority = input.nextInt();

		for (Meeting m : meetings) {
			if (inputID == m.getId()) {
				m.setSubject(subject);
				m.setStartDay(new Date(startDay + " " + startTime));
				m.setPriority(priority);
				System.out.println("Meeting updated! \n ");
				break;
			}
		}
	}

	public static void errorDisplay() {
		System.err.println("Error Message!\n" + "The meeting you are trying to create already exist or\n"
				+ "the time/date you are trying to use is already booked\n\n");
		System.out.println(
				"Choose one option\n" + "1 - to cancel; or\n" + "2 - to overlap the previous meeting schedule.\n");
	}

	public static void mainMenu() {
		System.out.print("Choose one option: \n" + "1 - Create a new meeting \n"
				+ "2 - Show meetings on the calendar \n" + "3 - Delete by Id \n" + "4 - Clear all meetings \n"
				+ "5 - Update Meeting \n" + "6 - Exit \n\n");
	}

	public boolean areYouSureMenu() {
		System.out.println("Are you sure you want to remove this?\n" + "1 - Yes\n" + "2 - No\n\n");

		int selection = input.nextInt();

		if (selection == 1) {
			return true;
		} else {
			return false;
		}
	}

	public void displayReport() {
		for (Meeting m : meetings) {
			System.out.println("Meeting's Id is: " + m.getId() + "\n" + "Meeting's subject is: " + m.getSubject() + "\n"
					+ "Meeting is scheduled for: " + m.getStartDay() + "\n" + "Meeting's priority is: "
					+ m.getPriority() + "\n");
		}
	}
}
