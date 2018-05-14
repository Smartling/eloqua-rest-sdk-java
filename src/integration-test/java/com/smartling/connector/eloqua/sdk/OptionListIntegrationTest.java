package com.smartling.connector.eloqua.sdk;

import com.google.common.collect.ImmutableList;
import com.smartling.connector.eloqua.sdk.client.ContactClient;
import com.smartling.connector.eloqua.sdk.client.LandingPageClient;
import com.smartling.connector.eloqua.sdk.client.OptionListClient;
import com.smartling.connector.eloqua.sdk.rest.model.Elements;
import com.smartling.connector.eloqua.sdk.rest.model.HtmlContent;
import com.smartling.connector.eloqua.sdk.rest.model.LandingPage;
import com.smartling.connector.eloqua.sdk.rest.model.Option;
import com.smartling.connector.eloqua.sdk.rest.model.OptionList;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OptionListIntegrationTest extends BaseIntegrationTest
{
    private static String OPTION_LIST_NAME = "optionListName";
    private static String OPTION_VALUE = "firstOption";
    private static String OPTION_DISPLAY_NAME = "First Option";
    private static String OPTION_TYPE = "Option";

    @Test
    public void shouldThrowAuthenticationExceptionIfPasswordIncorrect()
    {
        OptionListClient client = new OptionListClient(new Configuration(siteName, username, "invalid"));

        assertThatThrownBy(() -> client.listOptionLists(1, 10, "", "", ""))
                .isInstanceOf(EloquaAuthenticationException.class);
    }

    @Test
    public void shouldThrowObjectNotFoundException()
    {
        OptionListClient client = new OptionListClient(configuration);
        assertThatThrownBy(() -> client.getOptionList(100500)).isInstanceOf(EloquaObjectNotFoundException.class);
    }

    @Test
    public void shouldThrowApiExceptionIfApiCallFailed()
    {
        OptionListClient client = new OptionListClient(configuration);

        // 0 is invalid parameter so emulating an API error
        assertThatThrownBy(() -> client.listOptionLists(0, 10, "", "", ""))
                .isExactlyInstanceOf(EloquaClientException.class);
    }

    @Test
    public void shouldWorkWithOptionListsCorrectly()
    {
        OptionListClient optionListClient = new OptionListClient(configuration);

        Option option = new Option();

        option.setType(OPTION_TYPE);
        option.setValue(OPTION_VALUE);
        option.setDisplayName(OPTION_DISPLAY_NAME);

        final String optionListName1 = OPTION_LIST_NAME + RandomStringUtils.random(5);
        OptionList optionList1 = new OptionList();
        optionList1.setName(optionListName1);
        optionList1.setElements(ImmutableList.of(option));

        final String optionListName2 = OPTION_LIST_NAME + RandomStringUtils.random(5);
        OptionList optionList2 = new OptionList();
        optionList2.setName(optionListName2);
        optionList2.setElements(ImmutableList.of(option));

        OptionList newOptionList1 = optionListClient.createOptionList(optionList1);
        OptionList newOptionList2 = optionListClient.createOptionList(optionList2);

        OptionList foundOptionList1 = optionListClient.getOptionList(newOptionList1.getId());
        assertThat(foundOptionList1).isNotNull();
        assertThat(foundOptionList1.getName()).isEqualTo(optionListName1);
        assertThat(foundOptionList1.getElements()).isNotEmpty();
        assertThat(foundOptionList1.getElements().get(0)).isNotNull();
        assertThat(foundOptionList1.getElements().get(0).getValue()).isEqualTo(OPTION_VALUE);
        assertThat(foundOptionList1.getElements().get(0).getDisplayName()).isEqualTo(OPTION_DISPLAY_NAME);

        OptionList foundOptionList2 = optionListClient.getOptionList(newOptionList2.getId());
        assertThat(foundOptionList2).isNotNull();
        assertThat(foundOptionList2.getName()).isEqualTo(optionListName2);
        assertThat(foundOptionList2.getElements()).isNotEmpty();
        assertThat(foundOptionList2.getElements().get(0)).isNotNull();
        assertThat(foundOptionList2.getElements().get(0).getValue()).isEqualTo(OPTION_VALUE);
        assertThat(foundOptionList2.getElements().get(0).getDisplayName()).isEqualTo(OPTION_DISPLAY_NAME);

        final String updatedOptionListName = optionListName1 + POSTFIX;
        foundOptionList1.setName(updatedOptionListName);

        optionListClient.updateOptionList(foundOptionList1.getId(), foundOptionList1);

        Elements<OptionList> updatedOptionLists = optionListClient.searchForOptionList(updatedOptionListName);
        assertThat(updatedOptionLists).isNotNull();
        assertThat(updatedOptionLists.getElements()).isNotEmpty();
        assertThat(updatedOptionLists.getTotal()).isEqualTo(1);

        OptionList updatedOptionList = updatedOptionLists.getElements().get(0);
        assertThat(updatedOptionList.getName()).isEqualTo(updatedOptionListName);

        Elements<OptionList> sortedOptionLists = optionListClient.listOptionLists(1, 10, "name", "DESC", OPTION_LIST_NAME + "*");
        assertThat(sortedOptionLists).isNotNull();
        assertThat(sortedOptionLists.getPage()).isEqualTo(1);
        assertThat(sortedOptionLists.getPageSize()).isEqualTo(10);
        assertThat(sortedOptionLists.getTotal()).isGreaterThan(1);
        assertThat(sortedOptionLists.getElements()).isNotEmpty();
        assertThat(sortedOptionLists.getElements().get(0)).isNotNull();
        assertThat(sortedOptionLists.getElements().get(1)).isNotNull();

        assertThat(sortedOptionLists.getElements().get(0).getName().compareTo(sortedOptionLists.getElements().get(1).getName())).isLessThan(0);

        optionListClient.deleteOptionList(foundOptionList1.getId());
        optionListClient.deleteOptionList(foundOptionList2.getId());

        Elements<OptionList> optionLists = optionListClient.searchForOptionList(updatedOptionListName);
        assertThat(optionLists).isNotNull();
        assertThat(optionLists.getElements()).isEmpty();

        optionLists = optionListClient.searchForOptionList(optionListName2);
        assertThat(optionLists).isNotNull();
        assertThat(optionLists.getElements()).isEmpty();
    }
}
