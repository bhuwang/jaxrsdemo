/**
 * 
 */
package com.bhuwan.jaxrsdemo.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bhuwan.jaxrsdemo.model.Profile;
import com.bhuwan.jaxrsdemo.service.ProfileService;

/**
 * @author bhuwan
 *
 */
@Path("/profiles")
@Consumes(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class ProfileResource {

    ProfileService profileService = new ProfileService();

    @GET
    public List<Profile> getProfiles() {
        return profileService.getAllProfiles();
    }

    @GET
    @Path("/{profileName}")
    public Profile getProfile(@PathParam("profileName") String profileName) {
        return profileService.getProfile(profileName);
    }
    
    @POST
    public Profile addProfile(Profile message) {
        return profileService.addProfile(message);
    }
    
    @PUT
    @Path("/{profileName}")
    public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
        profile.setProfileName(profileName);
        return profileService.updateProfile(profile);
    }
    
    @DELETE
    @Path("/{profileName}")
    public void deleteProfile(@PathParam("profileName") String profileName) {
        profileService.removeProfile(profileName);
    }

}
