package org.caalpeva.starwars.controller;
class User {
  @Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", securitySocialNumber=" + securitySocialNumber + ", isAdmin=" + isAdmin
				+ "]";
	}
long id;
  String firstName;
  String lastName;
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
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getSecuritySocialNumber() {
	return securitySocialNumber;
}
public void setSecuritySocialNumber(String securitySocialNumber) {
	this.securitySocialNumber = securitySocialNumber;
}
public boolean isAdmin() {
	return isAdmin;
}
public void setAdmin(boolean isAdmin) {
	this.isAdmin = isAdmin;
}
String password;
  String securitySocialNumber;
  boolean isAdmin;
}