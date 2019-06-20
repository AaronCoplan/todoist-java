package com.aaroncoplan.todoist;

import com.aaroncoplan.todoist.helpers.LabelRequest;
import com.aaroncoplan.todoist.helpers.ProjectRequest;
import com.aaroncoplan.todoist.helpers.TaskRequest;
import com.aaroncoplan.todoist.model.Label;
import com.aaroncoplan.todoist.model.Project;
import com.aaroncoplan.todoist.model.Task;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Todoist {

    private final int HTTP_OK = 200;
    private final int HTTP_OK_NO_CONTENT = 204;

    private final String URL_BASE = "https://beta.todoist.com/API/v8";

    public Todoist(String token) {
        Unirest.setTimeouts(20_000, 20_000);
        Unirest.setDefaultHeader("Authorization", String.format("Bearer %s", token));
    }

    public List<Project> getAllProjects() {
        try {
            HttpResponse<String> response = Unirest.get(URL_BASE + "/projects")
                    .asString();
            if(response.getStatus() != HTTP_OK) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }
            return JsonAdapters.extractProjectList(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Project getProject(long id) {
        try {
            HttpResponse<String> response = Unirest.get(URL_BASE + "/projects/" + id)
                    .asString();
            if(response.getStatus() != HTTP_OK) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }
            return JsonAdapters.extractProject(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Project createNewProject(String name) {
        try {
            HttpResponse<String> response = Unirest.post(URL_BASE + "/projects")
                    .header("Content-Type", "application/json")
                    .body(JsonAdapters.writeProjectRequest(new ProjectRequest(name)))
                    .asString();
            if(response.getStatus() != HTTP_OK) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }
            return JsonAdapters.extractProject(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateProject(long id, String name) {
        try {
            HttpResponse<String> response = Unirest.post(URL_BASE + "/projects/" + id)
                    .header("Content-Type", "application/json")
                    .body(JsonAdapters.writeProjectRequest(new ProjectRequest(name)))
                    .asString();
            if(response.getStatus() != HTTP_OK_NO_CONTENT) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProject(long id) {
        try {
            HttpResponse<String> response = Unirest.delete(URL_BASE + "/projects/" + id)
                    .asString();
            if(response.getStatus() != HTTP_OK_NO_CONTENT) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Task> getActiveTasks() {
        try {
            HttpResponse<String> response = Unirest.get(URL_BASE + "/tasks")
                    .asString();
            if(response.getStatus() != HTTP_OK) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }

            return JsonAdapters.extractTaskList(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Task> getActiveTasks(Long projectId, Long labelId, String filter, String lang) {
        try {
            Map<String, Object> params = new HashMap<>();
            if(projectId != null) params.put("project_id", projectId);
            if(labelId != null) params.put("label_id", labelId);
            if(filter != null) params.put("filter", filter);
            if(lang != null) params.put("lang", lang);

            HttpResponse<String> response = Unirest.get(URL_BASE + "/tasks")
                    .queryString(params)
                    .asString();
            if(response.getStatus() != HTTP_OK) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }
            return JsonAdapters.extractTaskList(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Task createNewTask(TaskRequest taskRequest) {
        try {
            HttpResponse<String> response = Unirest.post(URL_BASE + "/tasks")
                    .header("Content-Type", "application/json")
                    .body(JsonAdapters.writeTaskRequest(taskRequest))
                    .asString();
            if(response.getStatus() != HTTP_OK) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }
            return JsonAdapters.extractTask(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Task createNewTask(String content) {
        return createNewTask(new TaskRequest(content, null, null, null, null, null, null, null, null));
    }

    public Task createNewTask(String content, Long projectId, Integer order, List<Long> labelIds, Integer priority, String dueString, String dueDate, String dueDateTime, String dueLang) {
        return createNewTask(new TaskRequest(content, projectId, order, labelIds, priority, dueString, dueDate, dueDateTime, dueLang));
    }

    public Task getActiveTask(long id) {
        try {
            HttpResponse<String> response = Unirest.get(URL_BASE + "/tasks/" + id)
                    .asString();
            if(response.getStatus() != HTTP_OK) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }
            return JsonAdapters.extractTask(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateTask(long id, String content, Long projectId, List<Long> labelIds, Integer priority, String dueString, String dueDate, String dueDateTime, String dueLang) {
        try {
            HttpResponse<String> response = Unirest.post(URL_BASE + "/tasks/" + id)
                    .header("Content-Type", "application/json")
                    .body(JsonAdapters.writeTaskRequest(new TaskRequest(content, projectId, null, labelIds, priority, dueString, dueDate, dueDateTime, dueLang)))
                    .asString();
            if(response.getStatus() != HTTP_OK_NO_CONTENT) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeTask(long id) {
        try {
            HttpResponse<String> response = Unirest.post(URL_BASE + "/tasks/" + id + "/close")
                    .asString();
            if(response.getStatus() != HTTP_OK_NO_CONTENT) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(long id) {
        try {
            HttpResponse<String> response = Unirest.delete(URL_BASE + "/tasks/" + id)
                    .asString();
            if(response.getStatus() != HTTP_OK_NO_CONTENT) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Label> getAllLabels() {
        try {
            HttpResponse<String> response = Unirest.get(URL_BASE + "/labels")
                    .asString();
            if(response.getStatus() != HTTP_OK) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }
            return JsonAdapters.extractLabelList(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Label createNewLabel(String name) {
        return createNewLabel(name, null);
    }

    public Label createNewLabel(String name, Integer order) {
        try {
            HttpResponse<String> response = Unirest.post(URL_BASE + "/labels")
                    .header("Content-Type", "application/json")
                    .body(JsonAdapters.writeLabelRequest(new LabelRequest(name, order)))
                    .asString();
            if(response.getStatus() != HTTP_OK) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }
            return JsonAdapters.extractLabel(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Label getLabel(long id) {
        try {
            HttpResponse<String> response = Unirest.get(URL_BASE + "/labels/" + id)
                    .asString();
            if(response.getStatus() != HTTP_OK) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }
            return JsonAdapters.extractLabel(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
