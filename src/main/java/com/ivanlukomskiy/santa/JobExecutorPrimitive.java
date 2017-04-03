package com.ivanlukomskiy.santa;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.ivanlukomskiy.santa.models.MessageContent;
import com.ivanlukomskiy.santa.models.Person;
import com.ivanlukomskiy.santa.models.Priority;
import com.ivanlukomskiy.santa.notifications.NotificationsService;

import java.util.*;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 02.04.2017.
 */
public class JobExecutorPrimitive implements JobExecutor {
    public static final int PERSONS_LIMIT = 50;

    public void execute(Job job) throws Exception {
        List<Person> persons = job.getPersonsProvider().loadPersons();
        List<Priority> priorities = job.getPrioritiesConfigProvider().getPrioritiesConfig().getPriorities();
        MessageContent messageContent = job.getMessageContentProvider().getMessageContent();
        NotificationsService notificationsService = job.getNotificationsService();
        if (persons.size() > PERSONS_LIMIT) {
            throw new Exception("Too much persons");
        }
        if (persons.size() < 2) {
            throw new Exception("Too less persons");
        }

        Multimap<Integer, List<Person>> sequencesByPriorityMatching = ArrayListMultimap.create();

        for (int i = 0; i < 200; i++) {
            List<Person> generated = generateSomehowList(persons, priorities);
//            System.out.print(getSequencePriorityMatching(generated, priorities));
//            System.out.println(Arrays.toString(generated.toArray()) + " ");
            sequencesByPriorityMatching.put(getSequencePriorityMatching(generated, priorities), generated);
        }

        int max = Collections.max(sequencesByPriorityMatching.keySet());
        Collection<List<Person>> bestSequences = sequencesByPriorityMatching.get(max);
        List<Person> chosenSequence = (List<Person>)
                bestSequences.toArray()[new Random().nextInt(bestSequences.size())];

        System.out.println("BEST MATCH:");
        System.out.print(getSequencePriorityMatching(chosenSequence, priorities));
        System.out.println(Arrays.toString(chosenSequence.toArray()) + " ");

        notificationsService.sendNotifications(chosenSequence, messageContent);
    }

    private int getSequencePriorityMatching(List<Person> persons, List<Priority> priorities) {
        int result = 0;
        for (int i = 0; i < persons.size(); i++) {
            result += priorityMatchng(persons.get(i), persons.get(i + 1 < persons.size() ? i + 1 : 0),
                    priorities);
        }
        return result;
    }

    private List<Person> generateSomehowList(List<Person> persons, List<Priority> priorities) {
        List<Person> copy = new LinkedList<>(persons);
        List<Person> res = new ArrayList<>(persons.size());
        Random r = new Random();
        Person start = copy.get(r.nextInt(copy.size()));
        copy.remove(start);
        res.add(start);
        for (int i = 0; i < persons.size() - 1; i++) {
            Multimap<Integer, Person> personsByPriority = ArrayListMultimap.create();
            for (Person copyPerson : copy) {
                personsByPriority.put(priorityMatchng(res.get(i), copyPerson, priorities), copyPerson);
            }
            ;
            Integer max = Collections.max(personsByPriority.keySet());
            Collection<Person> bestMatches = personsByPriority.get(max);
            Person choosen = (Person) bestMatches.toArray()[r.nextInt(bestMatches.size())];
            res.add(i + 1, choosen);
            copy.remove(choosen);
        }
        return res;
    }

    private int priorityMatchng(Person p1, Person p2, List<Priority> priorities) {
        int result = 0;
        for (int i = 0; i < priorities.size(); i++) {
            Priority pr = priorities.get(i);
            if (pr.getPriorityType() != Priority.PriorityType.PREFER_IF_DIFFERENT) {
                throw new UnsupportedOperationException("Unknown priority type");
            }
            String p1Val = p1.getParameters().get(pr.getFieldName());
            String p2Val = p2.getParameters().get(pr.getFieldName());
            if (!Objects.equals(p1Val, p2Val)) {
                result += Math.pow(2, priorities.size() - i);
            }
        }
        return result;
    }
}
