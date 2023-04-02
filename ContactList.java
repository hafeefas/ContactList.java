import java.util.*;

class ContactList implements Iterable<Contact> {
    private ArrayList<Contact> contactLists;


    public ContactList() {
        contactLists = new ArrayList<>();
        // ^^ A constructor that creates an empty ContactList.
    }

    public ContactList(Contact[] contacts) {
        this.contactLists = new ArrayList<>(Arrays.asList(contacts));
        Collections.sort(contactLists); // this will sort everything in the AL in natural order
        // ^^ A constructor that uses an array of Contacts in order to initialize the
        // ContactList
    }

    // note: binary search only works on sorted lists
    public Contact findByLastName(String last) {
        int first = 0;
        int end = contactLists.size() - 1;

        while (first <= end) {
            int mid = (first + end) / 2;
            if (last.equals(contactLists.get(mid).getLastName())) {
                return contactLists.get(mid);
            } else if (last.compareTo(contactLists.get(mid).getLastName()) > 0) {
                first = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        // if we reach here, then element was
        // not present
        return null;
        // A method that searches for a particular contact by last name, and returns a
        // reference
        // to the Contact. If there is no such Contact, a sentinel value (e.g. a default
        // valued
        // Contact or null) should be returned. Since the list is in sorted order, this
        // method
        // should be a binary search of the list.
    }


    public Contact findByPhoneNumber(String phone) {
        // A method that searches for a Contact by phone number, and returns a
        // reference to the Contact. If there is no such Contact, a sentinel value
        // should be returned.


        for (Contact contacts : contactLists) {
            if (contacts.getPhoneNumber().equals(phone)) {
                return contacts;
            }
            // Don't write the return null here, it will result in an error. the return
            // statement has to be outside the for loop.
        }
        return null; // returning -1 won't work because that's an int and not an object

    }


    ContactList findAllByLastInitial(char ch) {
        /*
         * A method that searches for and returns a ContactList containing all Contacts
         * with a last name starting with a particular letter. If there are no such
         * Contacts, return the empty ContactList.
         */

        // To make the empty contact list, just make a new contact list and make it be
        // equal to the original one
        ContactList old = new ContactList();


        for (Contact contacts : contactLists) { // this will iterate through everything in the contactLists array and
                                                // store the values in contacts
            if (contacts.getLastName().charAt(0) == (ch)) {
                old.add(contacts); // this will add all the values into the new ContactList we made
                // we know to do old.add(contacts) because it is asking to return a ContactList,
                // not the contactLists.
            }
        }
        return old;
        // always remember what u have to return and update. Since it said u have to
        // return the ContactList , u have to return the ContactList obj not the actual
        // arraylist
    }


    public boolean isEmpty() {
      return size() == 0;
    }

    public ContactList findAllByCity(String city) {
        // A method that searches for and returns a ContactList containing all Contacts
        // that live
        // in a particular city. If there are no such Contacts, you should return the
        // empty
        // ContactList.

        ContactList old = new ContactList();

        for (Contact contacts : contactLists) {
            if (contacts.getCity().equals(city)) {
                old.add(contacts);
                // return old.add(contacts); DONT DO THIS! it will not compile with the return
                // in front.
            }
        }
        return old;
    }

    public boolean add(Contact c) {
        // A method that allows you to add a Contact to the ContactList in sorted order.
        // You
        // should only add a Contact to the list if it is not there already. Use a
        // modified binary
        // search to determine this. The method returns true if the add is successful
        // and false if
        // it is unsuccessful.
        // ContactList old = new ContactList(); this isnt needed because its saying to
        // add stuff into the list. it is not saying to return a ContactList.


        int index = Collections.binarySearch(contactLists, c);

            if (index > 0) {
                return false;
                // this means that the index was found in c, so it returns a positive. Thats
                // what Collections.binarySearch does, if c is found in contactLists, it will
                // return the index of c, which is always positive. If contactLists does not
                // find the same elements in c, it will return a negative number.
            } else {
                contactLists.add(-(index) - 1, c);
                return true;
                // if the index is negative then it isnt in the list, do -(index)-1 to get the
                // insertion point
                // as collections bs returned -(insertion)-1, i need to do -(index)-1
            }
        }

    public int size() {
        return contactLists.size();
        // - A method that returns the size of the ContactList.
    }

    public Contact remove(Object obj) {

        // - A method that allows you to remove and return a Contact from the
        // ContactList.
        if (!(obj instanceof Contact)) { // checking if obj is a contact first
            return null; // if its not return false
            // we know to do the instanceof cuz Object obj is in the parameter.
        }
        Contact x = (Contact) obj;
        if (contactLists.remove(x)) {
            return x;
        }
        return null;
        // Returning obj by itself will not compile as it won't know to try and downcast
        // it to a Contact. this is why we did that.
    }

    public Contact get(int index) throws IndexOutOfBoundsException {
        // A method that allows the client to get a Contact from the ContactList by
        // index. An
        // IndexOutOfBoundsException should be thrown if that index doesn’t exist.
        if (index < 0 || index < contactLists.size() || index > contactLists.size()) {
            throw new IndexOutOfBoundsException("The index, " + index + "does not exist.");
        }
        return contactLists.get(index); // dont just say return index, it's important for the index to be taken from the
                                        // contactLists arraylist
    }
    public boolean equals(Object obj) {
        // An overridden equals() method. Let’s define one ContactList being equal to
        // another
        // if they contain the same Contacts in the same order.
        if (!(obj instanceof ContactList)) {
            return false;
        }
        ContactList checkingSame = (ContactList) obj;
        if (contactLists.size() == (checkingSame.size())) {
            return true;
        }
        for (int i = 0; i < contactLists.size(); i++) {
            // advanced loop here isnt really good cuz u wouldnt be able to do get(i) on che
            // checkingSame()
            if (contactLists.get(i).equals(checkingSame.get(i))) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        // - An overridden toString() method that creates a representation for a
        // ContactList.
        StringBuilder res = new StringBuilder();
        // res=" "; -this isnt needed.
        for (int i = 0; i < contactLists.size(); i++) {
            res.append(contactLists.get(i)).append("\n ");
            // doing res+= will take up more memory, its better to do .append
        }
        return res.toString();
    }


    @Override
    public Iterator<Contact> iterator() {
        return contactLists.iterator();
        // An iterator() method that allows you to iterate through a ContactList. (You
        // can
        // implement your own or use the ArrayList’s own iterator).
    }
}
