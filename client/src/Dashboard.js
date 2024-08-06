import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Dashboard.css';

const Dashboard = ({ role, setRole }) => {
  const [user, setUser] = useState(null);
  const [assignments, setAssignments] = useState([]);

  useEffect(() => {
    const getUserInfo = async () => {
      try {
        const response = await fetch('/user', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
          },
        });

        const data = await response.json();
        setUser(data);
        setRole(data.role);
      } catch (error) {
        console.error('Error fetching user info', error);
        setUser(null);
      }
    };

    getUserInfo();
  }, [setRole]);

  useEffect(() => {
    const getAssignments = async () => {
      try {
        const response = await fetch('/assignments', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
          },
        });

        const data = await response.json();
        setAssignments(data);
      } catch (error) {
        console.error('Error fetching assignments list', error);
        setAssignments([]);
      }
    };

    getAssignments();
  }, []);

  return (
    <div>
      {user ? (
        <div>
          <h1 style={{ color: "var(--darkest-color)" }}>Assignments:</h1>
          {assignments.length > 0 ? (
            assignments.map((assignment) => (
              <AssignmentBox key={assignment.id} assignment={assignment} />
            ))
          ) : (
            <h1>No assignments data yet...</h1>
          )}
        </div>
      ) : (
        <h1>No user data available...</h1>
      )}
    </div>
  );
};

const AssignmentBox = ({ assignment }) => {
  const navigate = useNavigate();

  const goToAssignment = () => {
    navigate(`/assignment/${assignment.id}`);
  };

  return (

    <div className="item-container">
      <label className="text-box">{assignment.name}</label>
      <div className="button-container">
        <button className="button" onClick={goToAssignment}>{assignment.status === "COMPLETED" ? "👁️" : "📝"}</button>
      </div>
    </div>
  );
};

export { Dashboard };