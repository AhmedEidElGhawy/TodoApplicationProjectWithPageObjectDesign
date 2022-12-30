package com.qacart.todo.testcases;

import com.qacart.todo.api.RegisterApi;
import com.qacart.todo.api.TasksApi;
import com.qacart.todo.base.BaseTest;
import com.qacart.todo.pages.NewTodoPage;
import com.qacart.todo.pages.TodoPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Todo Feature")
public class TodoTest extends BaseTest {
    @Story("Add Todo")
    @Test(description = "Should be able to add a new todo correctly")
    public void shouldBeAbleToAddNewTodo() {
        RegisterApi registerApi = new RegisterApi();
        registerApi.register();
        NewTodoPage newTodoPage = new NewTodoPage(getDriver());
        newTodoPage.load();
        injectCookiesToBrowser(registerApi.getCookies());
        String actualResult = newTodoPage
                .load()
                .addNewTodoTask("Learn Selenium WebDriver")
                .getTodoText();
        Assert.assertEquals(actualResult, "Learn Selenium WebDriver");
    }
    @Story("Delete Todo")
    @Test(description = "Should be able to delete a todo correctly")
    public void shouldBeAbleToDeleteTodo() {
        RegisterApi registerApi = new RegisterApi();
        registerApi.register();
        TasksApi tasksApi = new TasksApi();
        tasksApi.addTask(registerApi.getToken());
        TodoPage todoPage = new TodoPage(getDriver());
        todoPage.load();
        injectCookiesToBrowser(registerApi.getCookies());
        boolean isNoTodoMessageIsDisplayed = todoPage
                .load()
                .ClickOnDeleteButton().
                isNoTodosMessageDisplayed();
        Assert.assertTrue(isNoTodoMessageIsDisplayed);
    }
}




