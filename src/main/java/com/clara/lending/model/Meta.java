
package com.clara.lending.model;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {

    @JsonProperty("status")
    private int status;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("response_id")
    private String responseId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     *
     * @return
     *     The status
     */
    @JsonProperty("status")
    public int getStatus() {
        return status;
    }

    /**
     *
     * @param status
     *     The status
     */
    @JsonProperty("status")
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     *
     * @return
     *     The msg
     */
    @JsonProperty("msg")
    public String getMsg() {
        return msg;
    }

    /**
     *
     * @param msg
     *     The msg
     */
    @JsonProperty("msg")
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     *
     * @return
     *     The responseId
     */
    @JsonProperty("response_id")
    public String getResponseId() {
        return responseId;
    }

    /**
     *
     * @param responseId
     *     The response_id
     */
    @JsonProperty("response_id")
    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
