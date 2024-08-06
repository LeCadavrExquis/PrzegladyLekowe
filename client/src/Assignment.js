import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { Survey } from 'survey-react-ui';
import { Model } from 'survey-core';
import "survey-core/defaultV2.min.css";

const Assignment = () => {

    const [form, setForm] = useState(null);
    const [isCompleted, setIsCompleted] = useState(false);
    const { id } = useParams();
    const navigate = useNavigate();

    const fetchForm = async () => {

        try {
            const response = await fetch(`/assignment/${id}`, {
                method: "GET",
                headers: {
                    "Response-Type": "application/json"
                },
            });

            const data = await response.json();
            setForm(data);

            if (data.status === 'COMPLETED') {
                setIsCompleted(true);
            } else {
                setIsCompleted(false);
            }

        } catch (error) {
            console.error(error);
        }

    };

    const sendResult = async (survey) => {

        form.anwser = survey.data;

        try {
            const response = await fetch(`/assignment/${id}`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(form),
            });

            if (response.ok) {
                console.log("success sending the survey results");
            } else {
                console.error("error saving results");
            }

        } catch (error) {

            console.error("error while saving results", error);
        }
    }

    useEffect(() => { fetchForm(); }, []);

    if (!form) {
        return <div>no form yet...</div>;
    }
    const survey = new Model(form.form);

    if (!isCompleted) {
        survey.onComplete.add((result) => {
            sendResult(result);
            navigate("/app")
        });

    } else {
        survey.data = form.anwser;
        form.form = survey;
        survey.mode = "display";
    }
    return (
        <>
            <Survey model={survey}/>
        </>
    )
};

export { Assignment }