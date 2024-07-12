db = db.getSiblingDB("db");
db.users.insertOne({
    email: "user@example.com",
    firstName: "Jan",
    lastName: "Kowalski",
    password: "$2a$10$teMkltthUOj.8C/OJdlxt.mV1dNS4Na/Y8tnFt3z3A07WpKLy1.6q",
    role: "USER"
});
db.users.insertOne({
    email: "doctor@example.com",
    firstName: "Michalina",
    lastName: "Wisłocka",
    password: "$2a$10$N3sclaXADeXJ.FqgYucW9Oi9pKThRbcqt9fnZGShLo9XC6mSCuTHC",
    role: "DOCTOR"
});
db.template.insertOne({
    name: "Survey Template 1",
    form: {
        title: "Customer Feedback Survey",
        description: "Please provide your feedback.",
        questions: [
            {
                type: "text",
                name: "customerName",
                title: "What is your name?"
            },
            {
                type: "rating",
                name: "customerRating",
                title: "How would you rate our service?",
                isRequired: true,
                minRateDescription: "Very Poor",
                maxRateDescription: "Excellent"
            },
            {
                type: "comment",
                name: "customerComments",
                title: "Any additional comments?"
            }
        ]
    }
});