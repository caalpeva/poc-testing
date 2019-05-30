package org.caalpeva.starwars.controller;
class UserDTO {
  @Override
	public String toString() {
		return "UserDTO [id=" + id + ", firstName=" + firstName + ", email=" + email + "]";
	}
long id;
  String firstName;
  String email;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
}