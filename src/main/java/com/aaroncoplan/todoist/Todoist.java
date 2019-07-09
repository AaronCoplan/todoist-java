package com.aaroncoplan.todoist;

import com.aaroncoplan.todoist.helpers.*;
import com.aaroncoplan.todoist.model.*;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Todoist {

    private final int HTTP_OK = 200;
    private final int HTTP_OK_NO_CONTENT = 204;

    private final String URL_BASE = "https://beta.todoist.com/API/v8";

    public Todoist(String token) {
        Unirest.config()
                .connectTimeout(20_000)
                .socketTimeout(20_000)
                .addDefaultHeader("Authorization", String.format("Bearer %s", token));
    }

    public <T> T extract(CheckedFunction<String, T> extractionFunction, HttpResponse<String> httpResponse) throws TodoistException {
        try {
            return extractionFunction.apply(httpResponse.getBody());
        } catch (IOException e) {
            throw new TodoistException("Error mapping JSON to Object");
        }
    }

    public interface CheckedFunction<P, R> {
        R apply(P t) throws IOException;
    }

    public List<Project> getAllProjects() throws TodoistException {
        HttpResponse<String> response = Unirest.get(URL_BASE + "/projects")
                .asString();
        if (response.getStatus() != HTTP_OK) throw new TodoistException(response.getStatus());
        return extract(JsonAdapters::extractProjectList, response);
    }

    public Project getProject(long id) throws TodoistException {
        HttpResponse<String> response = Unirest.get(URL_BASE + "/projects/" + id)
                .asString();
        if(response.getStatus() != HTTP_OK) throw new TodoistException(response.getStatus());
        return extract(JsonAdapters::extractProject, response);
    }

    public Project createNewProject(String name) throws TodoistException {
        HttpResponse<String> response = Unirest.post(URL_BASE + "/projects")
                .header("Content-Type", "application/json")
                .body(JsonAdapters.writeProjectRequest(new ProjectRequest(name)))
                .asString();
        if(response.getStatus() != HTTP_OK) throw new TodoistException(response.getStatus());
        return extract(JsonAdapters::extractProject, response);
    }

    public void updateProject(long id, String name) throws TodoistException {
        HttpResponse<String> response = Unirest.post(URL_BASE + "/projects/" + id)
                .header("Content-Type", "application/json")
                .body(JsonAdapters.writeProjectRequest(new ProjectRequest(name)))
                .asString();
        if(response.getStatus() != HTTP_OK_NO_CONTENT) throw new TodoistException(response.getStatus());
    }

    public void deleteProject(long id) throws TodoistException {
        HttpResponse<String> response = Unirest.delete(URL_BASE + "/projects/" + id)
                .asString();
        if(response.getStatus() != HTTP_OK_NO_CONTENT) throw new TodoistException(response.getStatus());
    }

    public List<Task> getActiveTasks() throws TodoistException {
        HttpResponse<String> response = Unirest.get(URL_BASE + "/tasks")
                .asString();
        if(response.getStatus() != HTTP_OK) throw new TodoistException(response.getStatus());
        return extract(JsonAdapters::extractTaskList, response);
    }

    public List<Task> getActiveTasks(Long projectId, Long labelId, String filter, String lang) throws TodoistException {
        Map<String, Object> params = new HashMap<>();
        if(projectId != null) params.put("project_id", projectId);
        if(labelId != null) params.put("label_id", labelId);
        if(filter != null) params.put("filter", filter);
        if(lang != null) params.put("lang", lang);

        HttpResponse<String> response = Unirest.get(URL_BASE + "/tasks")
                .queryString(params)
                .asString();
        if(response.getStatus() != HTTP_OK) throw new TodoistException(response.getStatus());
        return extract(JsonAdapters::extractTaskList, response);
    }

    private Task createNewTask(TaskRequest taskRequest) throws TodoistException {
        HttpResponse<String> response = Unirest.post(URL_BASE + "/tasks")
                .header("Content-Type", "application/json")
                .body(JsonAdapters.writeTaskRequest(taskRequest))
                .asString();
        if(response.getStatus() != HTTP_OK) throw new TodoistException(response.getStatus());
        return extract(JsonAdapters::extractTask, response);
    }

    public Task createNewTask(String content) throws TodoistException {
        return createNewTask(new TaskRequest(content, null, null, null, null, null, null, null, null));
    }

    public Task createNewTask(String content, Long projectId, Integer order, List<Long> labelIds, Integer priority, String dueString, String dueDate, String dueDateTime, String dueLang) throws TodoistException {
        return createNewTask(new TaskRequest(content, projectId, order, labelIds, priority, dueString, dueDate, dueDateTime, dueLang));
    }

    public Task getActiveTask(long id) throws TodoistException {
        HttpResponse<String> response = Unirest.get(URL_BASE + "/tasks/" + id)
                .asString();
        if(response.getStatus() != HTTP_OK) throw new TodoistException(response.getStatus());
        return extract(JsonAdapters::extractTask, response);
    }

    public void updateTask(long id, String content, Long projectId, List<Long> labelIds, Integer priority, String dueString, String dueDate, String dueDateTime, String dueLang) throws TodoistException {
        HttpResponse<String> response = Unirest.post(URL_BASE + "/tasks/" + id)
                .header("Content-Type", "application/json")
                .body(JsonAdapters.writeTaskRequest(new TaskRequest(content, projectId, null, labelIds, priority, dueString, dueDate, dueDateTime, dueLang)))
                .asString();
        if(response.getStatus() != HTTP_OK_NO_CONTENT) throw new TodoistException(response.getStatus());
    }

    public void closeTask(long id) throws TodoistException {
        HttpResponse<String> response = Unirest.post(URL_BASE + "/tasks/" + id + "/close")
                .asString();
        if(response.getStatus() != HTTP_OK_NO_CONTENT) throw new TodoistException(response.getStatus());
    }

    public void deleteTask(long id) throws TodoistException {
        HttpResponse<String> response = Unirest.delete(URL_BASE + "/tasks/" + id)
                .asString();
        if(response.getStatus() != HTTP_OK_NO_CONTENT) throw new TodoistException(response.getStatus());
    }

    public List<Label> getAllLabels() throws TodoistException {
        HttpResponse<String> response = Unirest.get(URL_BASE + "/labels")
                .asString();
        if(response.getStatus() != HTTP_OK) throw new TodoistException(response.getStatus());
        return extract(JsonAdapters::extractLabelList, response);
    }

    public Label createNewLabel(String name) throws TodoistException {
        return createNewLabel(name, null);
    }

    public Label createNewLabel(String name, Integer order) throws TodoistException {
        HttpResponse<String> response = Unirest.post(URL_BASE + "/labels")
                .header("Content-Type", "application/json")
                .body(JsonAdapters.writeLabelRequest(new LabelRequest(name, order)))
                .asString();
        if(response.getStatus() != HTTP_OK) throw new TodoistException(response.getStatus());
        return extract(JsonAdapters::extractLabel, response);
    }

    public Label getLabel(long id) throws TodoistException {
        HttpResponse<String> response = Unirest.get(URL_BASE + "/labels/" + id)
                .asString();
        if(response.getStatus() != HTTP_OK) throw new TodoistException(response.getStatus());
        return extract(JsonAdapters::extractLabel, response);
    }

    public void updateLabel(long id, String name, Integer order) throws TodoistException {
        HttpResponse<String> response = Unirest.post(URL_BASE + "/labels/" + id)
                .header("Content-Type", "application/json")
                .body(JsonAdapters.writeLabelRequest(new LabelRequest(name, order)))
                .asString();
        if(response.getStatus() != HTTP_OK_NO_CONTENT) throw new TodoistException(response.getStatus());
    }

    public void deleteLabel(long id) throws TodoistException {
        HttpResponse<String> response = Unirest.delete(URL_BASE + "/labels/" + id)
                .asString();
        if(response.getStatus() != HTTP_OK_NO_CONTENT) throw new TodoistException(response.getStatus());
    }

    public List<Comment> getAllCommentsForProject(long id) {
        try {
            HttpResponse<String> response = Unirest.get(URL_BASE + "/comments")
                    .queryString("project_id", id)
                    .asString();
            if(response.getStatus() != HTTP_OK) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }
            return JsonAdapters.extractCommentList(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Comment> getAllCommentsForTask(long id) {
        try {
            HttpResponse<String> response = Unirest.get(URL_BASE + "/comments")
                    .queryString("task_id", id)
                    .asString();
            if(response.getStatus() != HTTP_OK) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }
            return JsonAdapters.extractCommentList(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Comment createNewComment(Long taskId, Long projectId, String content, AttachmentRequest attachmentRequest) {
        try {
            HttpResponse<String> response = Unirest.post(URL_BASE + "/comments")
                    .header("Content-Type", "application/json")
                    .body(JsonAdapters.writeCommentRequest(new CommentRequest(taskId, projectId, content, attachmentRequest)))
                    .asString();
            if(response.getStatus() != HTTP_OK) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }
            return JsonAdapters.extractComment(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Comment createNewCommentForTask(long id, String content) {
        return createNewComment(id, null, content, null);
    }

    public Comment createNewCommentForTask(long id, String content, AttachmentRequest attachmentRequest) {
        return createNewComment(id, null, content, attachmentRequest);
    }

    public Comment createNewCommentForProject(long id, String content) {
        return createNewComment(null, id, content, null);
    }

    public Comment createNewCommentForProject(long id, String content, AttachmentRequest attachmentRequest) {
        return createNewComment(null, id, content, attachmentRequest);
    }

    public Comment getComment(long id) {
        try {
            HttpResponse<String> response = Unirest.get(URL_BASE + "/comments/" + id)
                    .asString();
            if(response.getStatus() != HTTP_OK) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }
            return JsonAdapters.extractComment(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateComment(long id, String content) {
        try {
            HttpResponse<String> response = Unirest.post(URL_BASE + "/comments/" + id)
                    .header("Content-Type", "application/json")
                    .body(JsonAdapters.writeCommentRequest(new CommentRequest(null, null, content, null)))
                    .asString();
            if(response.getStatus() != HTTP_OK_NO_CONTENT) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteComment(long id) {
        try {
            HttpResponse<String> response = Unirest.delete(URL_BASE + "/comments/" + id)
                    .asString();
            if(response.getStatus() != HTTP_OK_NO_CONTENT) {
                throw new Exception("HTTP STATUS " + response.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Activity> getActivityForProject(long id) {
        return getActivityForProject(id, ActivityType.ALL);
    }

    public List<Activity> getActivityForProject(long id, ActivityType... types) {
        try {
            int limit = 30;
            int offset = 0;
            int count;

            List<String> activityTypes = Arrays.stream(types)
                    .flatMap(ActivityType::getStream)
                    .collect(Collectors.toList());

            List<Activity> activityList = new ArrayList<>();
            do {
                HttpResponse<String> response = Unirest.post("https://todoist.com/API/v8/activity/get")
                        .header("Content-Type", "application/json")
                        .body(JsonAdapters.writeActivityRequest(new ActivityRequest(limit, offset, activityTypes, null, null, true, id, true)))
                        .asString();
                if (response.getStatus() != HTTP_OK) {
                    throw new Exception("HTTP STATUS " + response.getStatus());
                }

                ActivityResponse activityResponse = JsonAdapters.extractActivityResponse(response.getBody());
                activityList.addAll(activityResponse.events);

                count = activityResponse.count;
                offset += limit;
            } while(offset < count);

            return activityList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Activity> getActivityForTask(long id) {
        return getActivityForTask(id, ActivityType.ALL);
    }

    public List<Activity> getActivityForTask(long id, ActivityType... types) {
        try {
            int limit = 30;
            int offset = 0;
            int count;

            List<String> activityTypes = Arrays.stream(types)
                    .flatMap(ActivityType::getStream)
                    .collect(Collectors.toList());

            List<Activity> activityList = new ArrayList<>();
            do {
                HttpResponse<String> response = Unirest.post("https://todoist.com/API/v8/activity/get")
                        .header("Content-Type", "application/json")
                        .body(JsonAdapters.writeActivityRequest(new ActivityRequest(limit, offset, activityTypes, id, true, true, null, null)))
                        .asString();
                if (response.getStatus() != HTTP_OK) {
                    throw new Exception("HTTP STATUS " + response.getStatus());
                }

                ActivityResponse activityResponse = JsonAdapters.extractActivityResponse(response.getBody());
                activityList.addAll(activityResponse.events);

                count = activityResponse.count;
                offset += limit;
            } while(offset < count);

            return activityList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
