/* See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * Esri Inc. licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.esri.geoportal.search;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.SecurityContext;

import com.esri.geoportal.context.AppUser;

/**
 * Opensearch service provider.
 */
@ApplicationPath("opensearch")
@Path("")
public class OpensearchService extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> resources = new HashSet<Class<?>>();
    resources.add(OpensearchService.class);
    return resources;
  }

  @GET
  public void get(@Suspended final AsyncResponse asyncResponse,
      @Context SecurityContext sc,
      @Context HttpServletRequest hsr) {
    AppUser user = new AppUser(hsr,sc);
    new SearchRequest(asyncResponse,user).execute(hsr);
  }
  
  @POST
  @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
  public void post(@Suspended final AsyncResponse asyncResponse,
      @Context SecurityContext sc,
      @Context HttpServletRequest hsr,
      MultivaluedMap<String, String> requestParams) {
    AppUser user = new AppUser(hsr,sc);
    new SearchRequest(asyncResponse,user).execute(hsr);
  }
  
  @POST
  @Consumes({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN,MediaType.WILDCARD})
  public void postString(@Suspended final AsyncResponse asyncResponse,
      @Context SecurityContext sc,
      @Context HttpServletRequest hsr,
      String body) {
    AppUser user = new AppUser(hsr,sc);
    new SearchRequest(asyncResponse,user).execute(hsr,body);
  }
  
  @GET
  @Path("/description")
  public void getDescription(@Suspended final AsyncResponse asyncResponse,
      @Context SecurityContext sc,
      @Context HttpServletRequest hsr) {
    AppUser user = new AppUser(hsr,sc);
    new SearchRequest(asyncResponse,user).execute(hsr);
  }
  
  @POST
  @Path("/description")
  public void postDescription(@Suspended final AsyncResponse asyncResponse, 
      @Context SecurityContext sc,
      @Context HttpServletRequest hsr) {
    AppUser user = new AppUser(hsr,sc);
    new SearchRequest(asyncResponse,user).execute(hsr);
  }

}
