package classes;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * A class to manager your contacts and meetings.
 */

public interface ContactManager {
	/**
	 * Add a new meeting to be held in the future.
	 * 
	 * @param contacts a list of contacts that will participate in the meeting
	 * @param date the date on which the meeting will take place
	 * @return the ID for the meeting
	 * @throws IllegalArgumentException if the meeting is set for a time in the past, or if any contact is unknown/non-existent
	 */
	int addFutureMeeting(Set<Contact> contacts, Calendar date);
	
	/**
	 * Returns the PAST meeting with the requested ID, or null if there is none.
	 * 
	 * @param id the ID for the meeting
	 * @return the meeting with the requested ID, or null if there is none
	 * @throws IllegalArgumentException if there is a meeting with that ID happening in the future
	 */
	PastMeeting getPastMeeting(int id);
	
	/**
	 * Returns the FUTURE meeting with the requested ID, or null if there is none.
	 * 
	 * @param id the ID for the meeting
	 * @return the meeting with the requested ID, or null if there is none
	 * @throws IllegalArgumentException if there is a meeting with that ID happening in the past
	 */
	FutureMeeting getFutureMeeting(int id);
	
	/**
	 * Returns the meeting with the requested ID, or null if there is none.
	 * 
	 * @param id the ID for the meeting
	 * @return the meeting with the requested ID, or null if there is none
	 */
	Meeting getMeeting(int id);
	
	/**
	 * Returns the list of future meetings scheduled with this contact.
	 * If there are none, the returned list will be empty.
	 * Otherwise, the list will be chronologically sorted and will not contain any duplicates.
	 * 
	 * @param contact one of the user's contacts
	 * @return the list of future meeting(s) scheduled with this contact (may be empty)
	 * @throws IllegalArgumentException if the contact does not exist
	 */
	List<Meeting> getFutureMeetingList(Contact contact);
	
	/**
	 * Returns the list of meetings that are scheduled for, or that took place on, the specified date.
	 * If there are none, the returned list will be empty.
	 * Otherwise, the list will be chronologically sorted and will not contain any duplicates.
	 * 
	 * @param date the date
	 * @return the list of meetings
	 */
	List<Meeting> getFutureMeetingList(Calendar date);
	
	/**
	 * Returns the list of past meetings in which this contact has participated.
	 * If there are none, the returned list will be empty.
	 * Otherwise, the list will be chronologically sorted and will not contain any duplicates.
	 * 
	 * @param contact one of the user's contacts
	 * @return the list of future meetings(s) scheduled with this contact (may be empty)
	 * @throws IllegalArgumentException if the contact does not exist
	 */
	List<PastMeeting> getPastMeetingList(Contact contact);
	
	/**
	 * Create a new record for a meeting that took place in the past.
	 * 
	 * @param contacts a list of participants
	 * @param date the date on which the meeting took place
	 * @param text messages to be added about the meeting
	 * @throws IllegalArgumentException if the list of contacts is empty or any of the contacts do no exist
	 * @throws NullPointerException if any of the arguments are null
	 */
	void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text);
	
	/**
	 * Add notes to a meeting.
	 * This method is used when a future meeting takes place and is then converted to a past meeting (with notes).
	 * It can also be used to add notes to a past meeting at a later date.
	 * 
	 * @param id the ID of the meeting
	 * @param text messages to be added about the meeting
	 * @throws IllegalArgumentException if the meeting does not exist
	 * @throws IllegalStateException if the meeting is set for a date in the future
	 * @throws NullPointerException if the notes are null
	 */
	void addMeetingNotes(int id, String text);
	
	/**
	 * Create a new contact with the specified name and notes.
	 * 
	 * @param name the name of the contact
	 * @param notes notes to be added about the contact
	 * @throws NullPointerException if the name or the notes are null
	 */
	void addNewContact(String name, String notes);
	
	/**
	 * Returns a list with the contacts whose name contains that string.
	 * 
	 * @param name the string to search for
	 * @return a list with the contacts whose name contains that string
	 * @throws NullPointerException if the parameter is null
	 */
	Set<Contact> getContacts(String name);
	
	/**
	 * Save all data to disk.
	 * This method must be executed when the program is closed and when/if the user requests it.
	 */
	void flush();
}