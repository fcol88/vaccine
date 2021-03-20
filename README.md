# Vaccine Tracker

Do you lie awake at night wondering if your friends and family have been vaccinated?

Do you often wonder how long it might be before you can be confident that your friends and family are safe?

Did you quite enjoy the "Is Thatcher Dead?" tracker, and wished that there were more sites doing something very simple that you could check willy-nilly?

Well, here you are. A COVID-19 vaccine tracker.

## What is it for?

Adding a list of your friends (or getting them all to add themselves) and marking down whether they've had their first and second vaccine, then viewing some very high-level stats about it on the main page.

That's it, really.

## How do I use it?

Well, it's a Spring Boot application. It could probably be much simpler, or use another, more efficient framework, but I mostly build Spring Boot apps for a living so Spring Boot it is.

Probably the simplest way to use it is to clone using Git, swap ddl-auto in src/main/resources/application.properties to create and then follow these steps:

https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku

You'll need to include a database, since it has a piddly little table to store your users in.

Once you've followed those steps, you should be able to just...visit the URL it deployed to and see it working.

Once you've deployed once, probably best to swap back to ddl-auto none just to stop it dropping everything. I was too lazy to add a proper build tool for the database.

If you've never used Git before, basically just install it (if you need to install it), open a terminal, and type "git clone " sans quotes, followed by a link you've copied from the little green button at the top of this page.

## Who can use it?

I made it for a Discord server I have with some friends. You could use it for, I dunno, your family, your friends, both, your street, your neighbourhood, some work colleagues? Who knows. It's not secure, mind. So...don't put anything important in it.

## How do I add people?

I mean...it's not complic-

Sure. OK.

Click Add a friend on the top nav, then enter their name and whether they've had their first and second dose of the vaccine. Click Save. I guess you could use it to track other things, but it's all geared around the two-dose vaccines.

Once you've added some folks, or had those folks add themselves, go back to the home page to see some stats.

When you get your vaccine, click "Friends," if you're particularly popular, use the pager to find your name (click Prev, Next or a number), and then choose "Update" - you can then make a change and save it. Good job doing your part to stamp out COVID-19!

Give your friends the link, and as a friendship group you can *rally round this app* and get all excited when it ticks closer towards a big ol' "Yes". If you want to, anyway.

## When it says "Yes" can I go back to normal?

Check your local guidelines but the overwhelming likelihood is **no, no you can't, and it's probably irresponsible if you do**. It's very important that you still follow the law and any guidelines. Even if, in your corner of the world, the law and guidelines are quite lax, use your common sense. Act as if you have it - act as if not following social distancing may put another person or someone close to them at risk.

## I would have done *x* differently...

Well, do it, then. Surprisingly, something that I did for fun on a Saturday isn't an enterprise-grade application.

## What tools did you use?

Spring Boot as a framework.

Bulma as a front-end framework. I've been learning it for funsies and you should check it out, it's neat.

The tiniest scooch of jQuery, just to make the hamburger work. Could it have been plain JS? Probably. Oh well.
