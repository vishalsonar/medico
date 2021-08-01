package com.sonar.vishal.medico.core;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.util.Logger;
import com.sonar.vishal.medico.common.util.LoggerMessage;
import com.sonar.vishal.medico.core.adapter.KeyRequestAdapter;
import com.sonar.vishal.medico.core.adapter.RequestAdapter;

@Path("/api")
public class Api {

	@POST
	@Path("/process/request")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response processRequest(String data) {
		JsonElement element = JsonParser.parseString(data);
		Logger.trace(getClass().getName(), LoggerMessage.REQUEST_RECEIVED + element.getAsJsonObject().get(Constant.HEADER).getAsJsonObject().toString());
		JsonObject responseObject = new RequestAdapter().process(element.getAsJsonObject());
		Logger.trace(getClass().getName(), LoggerMessage.RESPONSE_GENERATED + responseObject.get(Constant.HEADER).getAsJsonObject().toString());
		return Response.status(200).entity(responseObject.toString()).build();
	}

	@POST
	@Path("/process/key")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response processKeyRequest(String data) {
		JsonElement element = JsonParser.parseString(data);
		Logger.trace(getClass().getName(), LoggerMessage.REQUEST_RECEIVED + element.getAsJsonObject().get(Constant.HEADER).getAsJsonObject().toString());
		JsonObject responseObject = new KeyRequestAdapter().process(element.getAsJsonObject());
		Logger.trace(getClass().getName(), LoggerMessage.RESPONSE_GENERATED + responseObject.get(Constant.HEADER).getAsJsonObject().toString());
		return Response.status(200).entity(responseObject.toString()).build();
	}
}
