# Inserting preselected speakers data

# --- !Ups

INSERT INTO SPEAKER(id, name, email, bio, picture_url, twitter_id) values (
   1,
   'Guillaume Bort',
   'guillaumebort@someemail.com',
   'Guillaume Bort is the co-founder of Zenexity, the french Web Oriented Architecture company. Former J2EE expert, he worked several years on constructing Web frameworks for large scale companies including banks, until he decided to sum up his experience by creating Play framework focusing back on simplicity. He still leads development of the Play framework.',
   'https://secure.gravatar.com/avatar/adcd749d588278dbd255068c1d4b20d3?s=200',
   'guillaumebort');

INSERT INTO PROPOSAL(title, proposal, type, is_approved, keywords, speaker_id) values (
	'History of playframework',
	'Walk down the memory lane of how playframework got started',
	0,
	true,
	'play1,play2',
	1
);

INSERT INTO SPEAKER(id, name, email, bio, picture_url, twitter_id) values (
   2,
   'Maxime Dantec',
   'Warry@someemail.com',
   'Maxime is a UI/UX designer and developer @Typesafe since 2012, he previously held the same position at Zenexity.',
   'https://secure.gravatar.com/avatar/12fda944ce03f21eb4f7f9fdd7512a8e?s=200',
   'iamwarry');

INSERT INTO PROPOSAL(title, proposal, type, is_approved, keywords, speaker_id) values (
	'Working with assets',
	'So you want to build html5 application with play? I can help you with how to integrate various types of assets with Play',
	1,
	true,
	'play2,html5,assets',
	2
);

INSERT INTO SPEAKER(id, name, email, bio, picture_url, twitter_id) values (
    3,
   'Sadek Drobi',
   'sadache@someemail.com', 
   'Sadek Drobi, CTO of Zenexity, a software engineer specialized in design and implementation of enterprise applications with a particular focus on bridging the gap between the problem domain and the solution domain. As a core Play developer, he works on the architecture, design and implementation of the framework.',
   'https://secure.gravatar.com/avatar/d349588ba91256515f7e2aa315e8cfae?s=200',
   'sadache');
   
INSERT INTO PROPOSAL(title, proposal, type, is_approved, keywords, speaker_id) values (
	'I see your async and raise it with reactive',
	'Async is boring lets make it more reactive',
	1, 
	true,
	'play2,reactive,iteratee,enumerator',
	3
);
   
   
INSERT INTO SPEAKER(id, name, email, bio, picture_url, twitter_id) values (
  4,
  'James Roper',
  'jroper@someemail.com',
  'James is has a range of experience across open source projects and enterprise applications. Having worked forAtlassian, James is passionate about providing developers with the best tools to get the job done. James is now workingfor Typesafe, with Play Framework being his primary focus.',
  'https://secure.gravatar.com/avatar/dd48848f9904bdce25eb2a2824842f50?s=200',
  'jroper');

INSERT INTO PROPOSAL(title, proposal, type, is_approved, keywords, speaker_id) values (
	'Whats new in Play 3',
	'Playframework has come a long way. In this presentation I will talk about things that are coming in next major version of play. Prepared to be blown away',
	1,
	true,
	'play2,play3',
	4
);

# --- !Downs

DELETE FROM PROPOSAL;

DELETE FROM SPEAKER WHERE name in ('Guillaume Bort', 'Maxime Dantec', 'Sadek Drobi', 'James Roper');